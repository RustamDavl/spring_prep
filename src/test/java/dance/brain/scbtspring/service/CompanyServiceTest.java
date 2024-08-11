package dance.brain.scbtspring.service;

import dance.brain.scbtspring.annotation.InjectBean;
import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.listener.entity.EntityEvent;
import dance.brain.scbtspring.listener.entity.EntityPublisher;
import dance.brain.scbtspring.repository.CrudRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CrudRepository<Long, Company> companyRepository;

    @Mock
    private UserService userService;
    @Mock
    private EntityPublisher entityPublisher;
    private ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    private CompanyService companyService;

    private static final Long COMPANY_ID = 1L;

    @BeforeEach
    public void setUp() {
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
    }

    @Test
    void findById() {
//        doReturn(applicationEventPublisher).when(entityPublisher).getApplicationEventPublisher();
//        doReturn(Optional.of(new Company(COMPANY_ID, "Name")))
//                .when(companyRepository).findById(COMPANY_ID);
//        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);
//
//        assertThat(actualResult).isPresent();
//        var expected = new CompanyReadDto(COMPANY_ID);
//        assertThat(actualResult.get()).isEqualTo(expected);
//        verify(applicationEventPublisher).publishEvent(any(EntityEvent.class));
//        verifyNoMoreInteractions(entityPublisher, userService);

    }
}