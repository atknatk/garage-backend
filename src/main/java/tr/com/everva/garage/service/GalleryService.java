package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.model.dto.ResponseDto;
import tr.com.everva.garage.model.dto.account.UserGalleryDto;
import tr.com.everva.garage.model.dto.account.UserGalleryPhoneDto;
import tr.com.everva.garage.model.dto.gallery.GalleryCreateDto;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderCreateFromGalleryDto;
import tr.com.everva.garage.repository.GalleryRepository;
import tr.com.everva.garage.repository.UserRepository;
import tr.com.everva.garage.util.ContextUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    private final UserRepository userRepository;

    public GalleryService(GalleryRepository galleryRepository, UserRepository userRepository) {
        this.galleryRepository = galleryRepository;
        this.userRepository = userRepository;
    }

    /**
     * Galeri oluşturur ve ortak bilgileri alır eğer ortaklar(Telefon numaralarına göre) mevcut ise ortaklık bilgilerini kayıt eder.
     * Eğer ortaklar kayıtlı değil ise tmp kullanıcı oluşturur ve ortaklık bilgisini kayıt etmeye devam eder.
     *
     * @param dto
     * @return
     */
    @Transactional
    public ResponseDto create(GalleryCreateDto dto) {
        String id = ContextUtils.getCurrentUser().getId();
        List<ShareHolderCreateFromGalleryDto> shareHolderDtoList = dto.getShareHolderDtoList();

        // Ortakların telefon numaraları dto'dan alınıyor;
        List<String> phoneNumberList =
                shareHolderDtoList.stream().
                        map(ShareHolderCreateFromGalleryDto::getPhone)
                        .collect(Collectors.toList());

        // Ortakların telefon numaraları db'de olanlar fetch ediliyor;
        final List<UserGalleryPhoneDto> byPhoneList = userRepository.findByPhoneList(phoneNumberList);

        dto.getShareHolderDtoList().forEach(shareHolder -> {


            Optional<UserGalleryPhoneDto> existDb =
                    byPhoneList.stream().filter(l -> l.getPhone().equals(shareHolder.getPhone())).findFirst();

            // Ortağın telefon numarası db'de mevcut ise ortaklık oranı yazılıyor
            if (existDb.isPresent()) {
                UserGalleryPhoneDto userGalleryPhoneDto = existDb.get();



            }

        });


        userRepository.findById(id);

        return null;
    }
}
