package dance.brain.scbtspring.service;

import dance.brain.scbtspring.repository.CompanyRepository;
import dance.brain.scbtspring.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CrudRepository companyRepository;

    @Autowired
    public CompanyService(CrudRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CrudRepository getCompanyRepository() {
        return this.companyRepository;
    }
}
