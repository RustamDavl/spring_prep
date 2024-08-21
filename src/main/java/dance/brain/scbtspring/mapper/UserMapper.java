package dance.brain.scbtspring.mapper;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.dto.UserReadDto;
import dance.brain.scbtspring.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(CreateUpdateUserDto dto);

    List<UserReadDto> toDtoList(List<User> users);

    UserReadDto toDto(User user);
}
