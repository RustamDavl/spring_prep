package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.dto.PersonalInfo;
import dance.brain.scbtspring.dto.PersonalInfoWithCompany;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends
        JpaRepository<User, Long>,
        UserFilterRepository,
        RevisionRepository<User, Long, Long>,
        QuerydslPredicateExecutor<User> {

    Optional<User> findByUsername(String username);

    @Query("""
            select u from User u
            where u.firstname like %:firstName% and u.lastname like %:lastName%
            """)
    List<User> findAllBy(String firstName, String lastName);

    @Query(value = """
            select u.* from users u
            where u.username = :username
            """, nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Query("""
            update User u
            set u.role = :role
            where u.id in (:ids)
            """)
    @Modifying(clearAutomatically = true)
    int updateRoles(Role role, Long... ids);

    Optional<User> findFirstByOrderByIdDesc();

    List<User> findTop3ByBirthDateBeforeOrderByIdDesc(LocalDate localDate);

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findTop3ByBirthDateBefore(LocalDate localDate, Sort sort);

    //List<User> findAllBy(Pageable pageable);
//    @EntityGraph(attributePaths = "company")
    @EntityGraph(attributePaths = {"company"})
    @Query(value = """
            select u from User u
                        """, countQuery = """
            select count (distinct u.firstname)
             from User u 
            """)
    Page<User> findAllBy(Pageable pageable);

    List<PersonalInfo> findAllByCompanyId(Long id);

    @Query("""
            select u.firstname as firstname, 
            u.lastname as lastname, 
            u.birthDate as birthDate, 
            c.id as companyId, 
            c.name as companyName 
                        
            from User u
            join u.company c
            where c.id = :id
            """)
    List<PersonalInfoWithCompany> findAllByCompanyIdProjection(Long id);
}
