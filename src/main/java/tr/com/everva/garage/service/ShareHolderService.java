package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.ShareHolder;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.ShareHolderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShareHolderService {

    @PersistenceContext
    public EntityManager entityManager;


    private final ShareHolderRepository shareHolderRepository;

    public ShareHolderService(ShareHolderRepository shareHolderRepository) {
        this.shareHolderRepository = shareHolderRepository;
    }

    public List<ShareHolderDto> findAll() {
        List<ShareHolder> all = shareHolderRepository.findAll();
        return all.stream().map(ShareHolderDto::new).collect(Collectors.toList());
    }

    public ShareHolderDto create(@Valid ShareHolderDto shareHolderDto) {
        ShareHolder shareHolder = new ShareHolder(shareHolderDto);
        ShareHolder saved = shareHolderRepository.save(shareHolder);
        return new ShareHolderDto(saved);
    }

    public ShareHolderDto create(String userId, String galleryId, int shareHolding) {
        ShareHolder shareHolder = new ShareHolder();
        shareHolder.setGallery(new Gallery(galleryId));
        shareHolder.setUser(new User(userId));
        shareHolder.setShareHolding(shareHolding);
        ShareHolder saved = shareHolderRepository.save(shareHolder);
        return new ShareHolderDto(saved);
    }

    protected Optional<ShareHolderDto> findByUserAndGallery(String userId, String galleryId){
        Optional<ShareHolder> byUserAndGallery = shareHolderRepository.findByUserAndGallery(userId, galleryId);
        return byUserAndGallery.map(ShareHolderDto::new);
    }


}
