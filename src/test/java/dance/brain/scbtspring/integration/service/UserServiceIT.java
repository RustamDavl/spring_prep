package dance.brain.scbtspring.integration.service;

import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.integration.IntegrationTestBase;
import dance.brain.scbtspring.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;

    private static final String GOOGLE = "Google";
    private static final String AMAZON = "Amazon";
    private static final Long IVAN_ID = 1L;
    private static final Long GOOGLE_ID = 1L;
    private static final Long AMAZON_ID = 3L;

    @Autowired
    public UserServiceIT(UserService userService) {
        this.userService = userService;
    }


    @Test
    void getAll() {
        List<User> users = userService.getAll();
        assertThat(users).hasSize(5);
    }

    @Test
    void getById() {
        User ivan = userService.getById(IVAN_ID);
        assertThat(ivan).isNotNull();
        assertThat(ivan.getUsername()).isEqualTo("ivan@gmail.com");
    }

    @Test
    void create() {
        User user = createUser();
        User createdUser = userService.create(GOOGLE_ID, user);
        assertThat(createdUser.getCompany().getName()).isEqualTo(GOOGLE);
        assertThat(createdUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(createdUser.getBirthDate()).isEqualTo(user.getBirthDate());
        assertThat(createdUser.getLastname()).isEqualTo(user.getLastname());
        assertThat(createdUser.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void update() {
        User ivan = userService.getById(IVAN_ID);
        User newUser = createUser();
        User updated = userService.update(AMAZON_ID, ivan.getId(), newUser);
        User updatedUser = userService.getById(updated.getId());
        assertThat(updatedUser.getId()).isEqualTo(ivan.getId());
        assertThat(updatedUser.getUsername()).isEqualTo(newUser.getUsername());
        assertThat(updatedUser.getLastname()).isEqualTo(newUser.getLastname());
        assertThat(updatedUser.getBirthDate()).isEqualTo(newUser.getBirthDate());
        assertThat(updatedUser.getRole()).isEqualTo(newUser.getRole());
        assertThat(updatedUser.getCompany().getName()).isEqualTo(AMAZON);
    }

    @Test
    void deleteById() {
        userService.delete(IVAN_ID);
        List<User> users = userService.getAll();
        assertThat(users).hasSize(4);
    }

    private User createUser() {
        User user = new User();
        user.setBirthDate(LocalDate.now());
        user.setUsername("test@gmail.com");
        user.setFirstname("Test");
        user.setLastname("Testovich");
        user.setRole(Role.USER);
        return user;
    }
}
