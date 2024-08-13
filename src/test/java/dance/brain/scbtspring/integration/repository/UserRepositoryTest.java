package dance.brain.scbtspring.integration.repository;

import dance.brain.scbtspring.entity.Locale;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findByLike() {
        List<User> allBy = userRepository.findAllBy("a", "ov");
        allBy.forEach(System.out::println);
    }

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        ivan.setBirthDate(LocalDate.now());

        int result = userRepository.updateRoles(Role.USER, 1L, 5L);
        assertThat(result).isEqualTo(2);

        User sameIvan = userRepository.getById(1L);
        assertEquals(Role.USER, sameIvan.getRole());
    }
}