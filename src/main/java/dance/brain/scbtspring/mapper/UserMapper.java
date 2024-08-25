package dance.brain.scbtspring.mapper;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.dto.UserReadDto;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.service.CompanyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface UserMapper {

    User toEntity(CreateUpdateUserDto dto);

    @Mapping(source = "company", target = "companyReadDto")
    List<UserReadDto> toDtoList(List<User> users);

    @Mapping(source = "company", target = "companyReadDto")
    UserReadDto toDto(User user);
}
