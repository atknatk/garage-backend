package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.NotFoundException;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.dto.gallery.GalleryCreateDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderCreateFromGalleryDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.repository.GalleryRepository;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    private final UserService userService;

    private final ShareHolderService shareHolderService;

    public GalleryService(GalleryRepository galleryRepository, UserService userService, ShareHolderService shareHolderService) {
        this.galleryRepository = galleryRepository;
        this.userService = userService;
        this.shareHolderService = shareHolderService;
    }

    /**
     * Galeri oluşturur ve ortak bilgileri alır eğer ortaklar(Telefon numaralarına göre) mevcut ise ortaklık bilgilerini kayıt eder.
     * Eğer ortaklar kayıtlı değil ise tmp kullanıcı oluşturur ve ortaklık bilgisini kayıt etmeye devam eder.
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackOn = {Exception.class})
    public ResponseDto create(GalleryCreateDto dto) {

        String id = ContextUtils.getCurrentUser().getId();
        UserDto currentUser = userService.findById(id);
        boolean existCurrentUser = dto.getShareHolderDtoList().stream().anyMatch(l -> l.getPhone().contains(currentUser.getPhone()));
        if (!existCurrentUser)
            throw new NotFoundException("İstek yapan kullanıcı galeri kullanıcılarında mevcut değil.");

        List<ShareHolderCreateFromGalleryDto> shareHolderDtoList = dto.getShareHolderDtoList();

        // ortakları olmanya galeri oluşturuluyor.
        final Gallery savedGallery = galleryRepository.save(new Gallery(dto));
        GalleryContext.setCurrentGallery(savedGallery.getId());

        // Ortakların telefon numaraları dto'dan alınıyor;
        List<String> phoneNumberList =
                shareHolderDtoList.stream().
                        map(ShareHolderCreateFromGalleryDto::getPhone)
                        .collect(Collectors.toList());

        // Ortakların telefon numaraları db'de olanlar fetch ediliyor;
        final List<UserDto> byPhoneList = userService.findByPhoneList(phoneNumberList);

        dto.getShareHolderDtoList().forEach(shareHolder -> {

            Optional<UserDto> existDb =
                    byPhoneList.stream().filter(l -> l.getPhone().equals(shareHolder.getPhone())).findFirst();

            // Ortağın telefon numarası db'de mevcut ise ortaklık oranı yazılıyor
            if (existDb.isPresent()) {
                UserDto userDto = existDb.get();

                // Eğer daha önceden bir kullanıcının aynı galeri üzerinde ortaklığı bulunuyor ise mevcut ortaklığı güncellenir.
                Optional<ShareHolderDto> byShareHolder = shareHolderService.findByUser(userDto.getId());

                // Galeri ile kullanıcı bağlanıyor.
                userService.assignGallery(userDto.getId(), savedGallery);

                if (byShareHolder.isPresent()) {
                    ShareHolderDto shareHolderDto = byShareHolder.get();
                    shareHolderDto.setShareHolding(shareHolder.getShareHolding());
                    shareHolderService.create(shareHolderDto);
                } else {
                    shareHolderService
                            .create(userDto.getId(), savedGallery.getId(), shareHolder.getShareHolding());
                }
            } else {
                // Ortağın telefon numarası db de kayıtlı değil
                UserDto passiveUser = userService.createPassiveUser(shareHolder.getPhone(), savedGallery);
                shareHolderService
                        .create(passiveUser.getId(), savedGallery.getId(), shareHolder.getShareHolding());
            }
        });
        return ResponseDto.builder().success(true).build();
    }
}
