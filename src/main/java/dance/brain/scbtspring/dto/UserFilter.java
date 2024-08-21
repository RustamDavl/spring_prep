package dance.brain.scbtspring.dto;

import java.time.LocalDate;

public record UserFilter(String firstname, String lastname, LocalDate birthDate) {
}
