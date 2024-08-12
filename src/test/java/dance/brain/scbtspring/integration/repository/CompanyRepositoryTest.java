package dance.brain.scbtspring.integration.repository;

import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.entity.Locale;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@IT
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    private final CompanyRepository companyRepository;

    @Autowired
    CompanyRepositoryTest(EntityManager entityManager, CompanyRepository companyRepository) {
        this.entityManager = entityManager;
        this.companyRepository = companyRepository;
    }

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1L);
        assertThat(company).isNotNull();
    }

    @Test
    void save() {
        Company company = Company.builder()
                .name("Sovcombank")
                .build();
        company.addLocale(Locale.builder()
                .lang("en")
                .description("english description")
                .build());
        entityManager.persist(company);
        assertThat(company.getId()).isNotNull();
    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(4L);
        assertThat(maybeCompany).isPresent();
        maybeCompany.ifPresent(company -> companyRepository.deleteById(4L));
        entityManager.flush();
        Optional<Company> afterDelete = companyRepository.findById(4L);
        assertThat(afterDelete).isEmpty();
    }

    @Test
    void testNamedQuery() {
        List<Company> some = entityManager.createNamedQuery("findCompanies", Company.class).getResultList();
        System.out.println(some);
    }
}