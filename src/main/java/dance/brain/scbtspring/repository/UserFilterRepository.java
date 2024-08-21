package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.dto.UserAndCompanylInfo;
import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.User;

import java.util.List;

public interface UserFilterRepository {

    List<User> findAllByUserFilter(UserFilter userFilter);

    List<UserAndCompanylInfo> findAllByCompanyIdJdbc(Long companyId);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
