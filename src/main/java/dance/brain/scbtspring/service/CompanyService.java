package dance.brain.scbtspring.service;

import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.listener.entity.AccessType;
import dance.brain.scbtspring.listener.entity.EntityEvent;
import dance.brain.scbtspring.listener.entity.EntityPublisher;
import dance.brain.scbtspring.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;

    private final EntityPublisher entityPublisher;

    @Autowired
    public CompanyService(CrudRepository<Integer, Company> companyRepository, EntityPublisher entityPublisher) {
        this.companyRepository = companyRepository;
        this.entityPublisher = entityPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    entityPublisher.getApplicationEventPublisher().publishEvent(new EntityEvent(entity, AccessType.SELECT));
                    return new CompanyReadDto(entity.id());
                });
    }

    public CrudRepository<Integer, Company> getCompanyRepository() {
        return this.companyRepository;
    }
}
