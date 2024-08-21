package dance.brain.scbtspring.service;

import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.listener.entity.AccessType;
import dance.brain.scbtspring.listener.entity.EntityEvent;
import dance.brain.scbtspring.listener.entity.EntityPublisher;
import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final UserService userService;
    private final EntityPublisher entityPublisher;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, UserService userService, EntityPublisher entityPublisher) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.entityPublisher = entityPublisher;
    }


    public Optional<CompanyReadDto> findById(Long id) {
        return companyRepository.findById(id)
                .map(entity -> {
                    entityPublisher.getApplicationEventPublisher().publishEvent(new EntityEvent(entity, AccessType.SELECT));
                    return new CompanyReadDto(entity.getId(), null);
                });
    }

    public CompanyRepository getCompanyRepository() {
        return this.companyRepository;
    }
}
