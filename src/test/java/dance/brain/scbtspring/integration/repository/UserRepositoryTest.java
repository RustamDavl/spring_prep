package dance.brain.scbtspring.integration.repository;

import dance.brain.scbtspring.dto.PersonalInfo;
import dance.brain.scbtspring.dto.PersonalInfoWithCompany;
import dance.brain.scbtspring.dto.UserAndCompanylInfo;
import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.Locale;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.integration.IntegrationTestBase;
import dance.brain.scbtspring.integration.annotation.IT;
import dance.brain.scbtspring.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class UserRepositoryTest extends IntegrationTestBase {

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
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id"));
        Page<User> page = userRepository.findAllBy(pageable);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        page.getContent().forEach(user -> System.out.println(user.getCompany().getName()));

        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.getContent().forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void findAllByCompanyId_Class_Projection() {
        List<PersonalInfo> allByCompanyId = userRepository.findAllByCompanyId(1L);
        System.out.println(allByCompanyId);
    }

    @Test
    void findAllByCompanyId_Interface_Projection() {
        List<PersonalInfoWithCompany> list = userRepository.findAllByCompanyIdProjection(1L);
        list.forEach(personalInfoWithCompany -> System.out.println(personalInfoWithCompany.getCompanyId() + "\n" +
                                                                   personalInfoWithCompany.getCompanyName() + "\n" +
                                                                   personalInfoWithCompany.getFirstname() + "\n" +
                                                                   personalInfoWithCompany.getBirthDate() + "\n" +
                                                                   personalInfoWithCompany.getFullName() + "\n"));
    }

    @Test
    void checkCustomImplementation() {
        UserFilter userFilter = new UserFilter(null, "ov", LocalDate.now());
        List<User> users = userRepository.findAllByUserFilter(userFilter);
        System.out.println(users);
    }

    @Test
    void checkAuditing() {
        Optional<User> ivan = userRepository.findById(1L);
        ivan.get().setBirthDate(ivan.get().getBirthDate().plusYears(1));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        List<UserAndCompanylInfo> users = userRepository.findAllByCompanyIdJdbc(3L);
        users.forEach(System.out::println);
    }

    @Test
    void checkBatchUpdate() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
    }
}