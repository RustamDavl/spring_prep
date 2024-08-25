package dance.brain.scbtspring.mapper;

import dance.brain.scbtspring.dto.CompanyReadDto;
import dance.brain.scbtspring.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyReadDto toDto(Company company);

    List<CompanyReadDto> toDtoList(List<Company> companies);
}
