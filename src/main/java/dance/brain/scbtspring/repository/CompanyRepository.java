package dance.brain.scbtspring.repository;


import dance.brain.scbtspring.entity.Company;
import jakarta.persistence.NamedQuery;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NamedQuery(name = "Company.findCompanies",
        query = """
        select c from Company c
        """)
public interface CompanyRepository extends Repository<Company, Long> {
    Optional<Company> findById(Long id);

    void deleteById(Long id);


    List<Company> findCompanies();

}
