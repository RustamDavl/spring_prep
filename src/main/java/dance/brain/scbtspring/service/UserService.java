package dance.brain.scbtspring.service;

import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAll();

    List<User> getAll(UserFilter filter);

    Page<User> getAll(UserFilter userFilter, Pageable pageable);

    User getById(Long id);

    User create(Long companyId, User user);

    User update(Long companyId, Long id, User user);

    void delete(Long id);
}
