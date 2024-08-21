package dance.brain.scbtspring.dto;

import java.time.LocalDate;

public record UserAndCompanylInfo(String firstname,
                                  String lastname,
                                  LocalDate birthDate,
                                  Long companyId,
                                  String companyName) {
}
