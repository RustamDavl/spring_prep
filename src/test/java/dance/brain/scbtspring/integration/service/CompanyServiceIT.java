package dance.brain.scbtspring.integration.service;

import dance.brain.scbtspring.config.DatabaseProperties;
import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@IT
public class CompanyServiceIT {

    private static final Long COMPANY_ID = 1L;


    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Autowired
    public CompanyServiceIT(CompanyService companyService, DatabaseProperties databaseProperties) {
        this.companyService = companyService;
        this.databaseProperties = databaseProperties;
    }

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
        assertThat(actualResult).isPresent();
        var expected = new CompanyReadDto(COMPANY_ID);
        assertThat(actualResult.get()).isEqualTo(expected);
    }
}
