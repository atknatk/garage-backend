package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.model.entity.Tenant;
import tr.com.everva.garage.repository.TenantRepository;

import javax.transaction.Transactional;

@Service
public class TenantService {


    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }


    @Transactional
    public Tenant saveEmptyTenant() {
        Tenant tenant = new Tenant();
        return tenantRepository.save(tenant);
    }


}
