package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.filter.NoGalleryFilter;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.ShareHolder;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.ShareHolderRepository;
import tr.com.everva.garage.util.GalleryContext;

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

    public void create(@Valid ShareHolderDto shareHolderDto) {
        ShareHolder shareHolder = new ShareHolder(shareHolderDto);
        shareHolder.setGallery(GalleryContext.getCurrentGalleryInstance());
        shareHolderRepository.save(shareHolder);

    }

    @NoGalleryFilter
    public void create(String userId, String galleryId, int shareHolding) {
        ShareHolder shareHolder = new ShareHolder();
        shareHolder.setGallery(new Gallery(galleryId));
        shareHolder.setUser(new User(userId));
        shareHolder.setShareHolding(shareHolding);
        shareHolderRepository.save(shareHolder);

    }

    protected Optional<ShareHolderDto> findByUser(String userId) {
        Optional<ShareHolder> byUserAndGallery = shareHolderRepository.findByUser(new User(userId));
        return byUserAndGallery.map(ShareHolderDto::new);
    }


}
