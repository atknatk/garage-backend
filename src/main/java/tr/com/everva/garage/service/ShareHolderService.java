package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.mapper.ShareHolderMapper;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.ShareHolder;
import tr.com.everva.garage.repository.ShareHolderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
        return ShareHolderMapper.INSTANCE.toDtoList(all);
    }


}
