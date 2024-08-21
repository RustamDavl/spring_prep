package dance.brain.scbtspring.dto;

import dance.brain.scbtspring.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateUpdateUserDto {

    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    String role;
    Long companyId;
}
