package dance.brain.scbtspring.service.impl;

import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.exception.EntityNotFoundException;
import dance.brain.scbtspring.listener.entity.AccessType;
import dance.brain.scbtspring.listener.entity.EntityEvent;
import dance.brain.scbtspring.listener.entity.EntityPublisher;
import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.service.CompanyService;
import dance.brain.scbtspring.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final EntityPublisher entityPublisher;

    public CompanyServiceImpl(CompanyRepository companyRepository, EntityPublisher entityPublisher) {
        this.companyRepository = companyRepository;
        this.entityPublisher = entityPublisher;
    }

    @Override
    public Company getById(Long id) {
        Company maybeCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found."));
        entityPublisher.getApplicationEventPublisher().publishEvent(new EntityEvent(maybeCompany, AccessType.SELECT));
        return maybeCompany;
    }

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
