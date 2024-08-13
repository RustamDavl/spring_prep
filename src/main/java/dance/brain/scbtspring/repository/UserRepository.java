package dance.brain.scbtspring.repository;

import dance.brain.scbtspring.database.ConnectionPool;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

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
}
