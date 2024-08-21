package dance.brain.scbtspring.service.impl;

import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.exception.UserAlreadyExistsException;
import dance.brain.scbtspring.exception.UserNotFoundException;
import dance.brain.scbtspring.repository.UserRepository;
import dance.brain.scbtspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User maybeUser = getById(id);
        updateFields(maybeUser, user);
        return userRepository.save(maybeUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void updateFields(User fromDb, User newUser) {
        fromDb.setUsername(newUser.getUsername());
        fromDb.setFirstname(newUser.getFirstname());
        fromDb.setLastname(newUser.getLastname());
        fromDb.setBirthDate(newUser.getBirthDate());
        fromDb.setRole(newUser.getRole());
    }
}
