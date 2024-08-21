package dance.brain.scbtspring.dto;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface PersonalInfoWithCompany {
    String getFirstname();

    String getLastname();

    LocalDate getBirthDate();

    String getCompanyId();

    String getCompanyName();

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
