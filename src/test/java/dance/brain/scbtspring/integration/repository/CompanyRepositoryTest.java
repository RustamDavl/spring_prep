package dance.brain.scbtspring.integration.repository;

import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.entity.Locale;
import dance.brain.scbtspring.integration.annotation.IT;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@Transactional
@Commit
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Autowired
    CompanyRepositoryTest(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1L);
        assertThat(company).isNotNull();
        assertThat(company.getLocales()).hasSize(2);
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
}