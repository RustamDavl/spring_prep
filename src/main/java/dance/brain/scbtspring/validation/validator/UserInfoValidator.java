package dance.brain.scbtspring.validation.validator;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.validation.UserInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class UserInfoValidator implements ConstraintValidator<UserInfo, CreateUpdateUserDto> {
    @Override
    public boolean isValid(CreateUpdateUserDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) && StringUtils.hasText(value.getLastname());
    }
}
