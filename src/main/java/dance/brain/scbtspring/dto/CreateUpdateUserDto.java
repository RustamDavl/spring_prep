package dance.brain.scbtspring.dto;

import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.validation.UserInfo;
import dance.brain.scbtspring.validation.groups.OnCreate;
import dance.brain.scbtspring.validation.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@UserInfo(groups = OnCreate.class)
public class CreateUpdateUserDto {

    @Email
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @NotBlank
    @Size(min = 3, max = 64)
    String firstname;

    @NotBlank
    String lastname;

    String role;

    Long companyId;
}
