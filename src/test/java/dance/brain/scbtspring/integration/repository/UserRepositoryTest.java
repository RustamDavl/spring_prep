package dance.brain.scbtspring.integration.repository;

import dance.brain.scbtspring.entity.Locale;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    void findTopLast() {
        Optional<User> lastUser = userRepository.findFirstByOrderByIdDesc();
        Assertions.assertThat(lastUser).isPresent();
        Assertions.assertThat(lastUser.get().getId()).isEqualTo(5L);
    }

    @Test
    void check() {
        List<User> list = userRepository.findTop3ByBirthDateBeforeOrderByIdDesc(LocalDate.now());
        assertThat(list).hasSizeBetween(0, 3);
    }

    @Test
    void checkSort() {
        Sort sortBy = Sort.by("firstname").and(Sort.by("lastname"));
        Sort.TypedSort<User> sort = Sort.sort(User.class);
        Sort sortBy2 = sort.by(User::getFirstname).and(sort.by(User::getLastname));

        userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortBy2);
    }
    @Test
    void checkPageable() {
        Pageable pageable = PageRequest.of(1, 2, Sort.by("id"));
        List<User> users = userRepository.findAllBy(pageable);
        users.forEach(System.out::println);
    }
}