package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.mapper.ShareHolderMapper;
import tr.com.everva.garage.model.dto.shareholder.ShareHolderDto;
import tr.com.everva.garage.model.entity.Income;
import tr.com.everva.garage.model.entity.ShareHolder;
import tr.com.everva.garage.repository.IncomeRepository;
import tr.com.everva.garage.repository.ShareHolderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class IncomeService {

    @PersistenceContext
    public EntityManager entityManager;


    private final IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> saveAll(Iterable<Income> income) {
        return incomeRepository.saveAll(income);
    }
}
