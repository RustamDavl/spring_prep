package dance.brain.scbtspring.service.impl;

import com.querydsl.core.types.Predicate;
import dance.brain.scbtspring.database.querydsl.QPredicate;
import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.Company;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.exception.EntityNotFoundException;
import dance.brain.scbtspring.repository.UserRepository;
import dance.brain.scbtspring.service.CompanyService;
import dance.brain.scbtspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dance.brain.scbtspring.entity.QUser.user;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CompanyService companyService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CompanyService companyService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAll(UserFilter userFilter) {
        return userRepository.findAllByUserFilter(userFilter);
    }

    @Override
    public Page<User> getAll(UserFilter userFilter, Pageable pageable) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.firstname(), user.firstname::containsIgnoreCase)
                .add(userFilter.lastname(), user.lastname::containsIgnoreCase)
                .add(userFilter.birthDate(), user.birthDate::before)
                .build();
        return userRepository.findAll(predicate, pageable);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    @Transactional
    public User create(Long companyId, User user) {
        if (companyId == null) {
            return userRepository.save(user);
        }
        Company company = companyService.getById(companyId);
        user.setCompany(company);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long companyId, Long id, User user) {
        User maybeUser = getById(id);
        if (companyId == null) {
            updateFields(maybeUser, user);
            return userRepository.saveAndFlush(maybeUser);
        }
        Company company = companyService.getById(companyId);
        updateFields(maybeUser, user);
        maybeUser.setCompany(company);
        return userRepository.saveAndFlush(maybeUser);
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
