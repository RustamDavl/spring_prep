package dance.brain.scbtspring.mapper;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.dto.UserReadDto;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.service.CompanyService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(CreateUpdateUserDto dto);

    @Mapping(source = "company", target = "companyReadDto")
    List<UserReadDto> toDtoList(List<User> users);

    @Mapping(source = "company", target = "companyReadDto")
    UserReadDto toDto(User user);

    @AfterMapping
    default void setPassword(CreateUpdateUserDto dto, @MappingTarget User user) {
        Optional.ofNullable(dto.getRawPassword())
                .filter(StringUtils::hasText)
                .map(rawPassword -> PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword))
                .ifPresent(user::setPassword);
    }

//    @Autowired
//    default PasswordEncoder getPasswordEncoder(PasswordEncoder passwordEncoder) {
//        passwordEncoder.
//        return passwordEncoder;
//    }

//    conditionExpression = "java(dto.getRawPassword != null)",
//    expression = "java(org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(dto.getRawPassword()))")
}
