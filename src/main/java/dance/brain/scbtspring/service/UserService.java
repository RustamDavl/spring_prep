package dance.brain.scbtspring.service;

import dance.brain.scbtspring.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User create(User user);

    User update(Long id, User user);

    void delete(Long id);
}
