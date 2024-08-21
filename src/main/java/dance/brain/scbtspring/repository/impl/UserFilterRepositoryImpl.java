package dance.brain.scbtspring.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import dance.brain.scbtspring.database.querydsl.QPredicate;
import dance.brain.scbtspring.database.rowmapper.UserAndCompanyInfoRowMapper;
import dance.brain.scbtspring.dto.UserAndCompanylInfo;
import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.repository.UserFilterRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static dance.brain.scbtspring.entity.QUser.user;

@Repository
public class UserFilterRepositoryImpl implements UserFilterRepository {

    private final EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserAndCompanyInfoRowMapper rowMapper;

    private static final String FIND_ALL_BY_COMPANY_ID = """
                SELECT u.firstname, u.lastname, u.birth_date, c.id as companyId, c.name as companyName
                FROM users u
                JOIN company c ON c.id = u.company_id
                WHERE c.id = ?        
            """;

    private static final String UPDATE_COMPANY_AND_ROLE = """
               update users
               set company_id = ?, role = ?
               where id = ?      
            """;

    private static final String NAMED_UPDATE_COMPANY_AND_ROLE = """
               update users
               set company_id = :companyId, role = :role
               where id = :id      
            """;

    @Autowired
    public UserFilterRepositoryImpl(EntityManager entityManager, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserAndCompanyInfoRowMapper rowMapper) {
        this.entityManager = entityManager;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<User> findAllByUserFilter(UserFilter userFilter) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.firstname(), user.firstname::containsIgnoreCase)
                .add(userFilter.lastname(), user.lastname::containsIgnoreCase)
                .add(userFilter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<UserAndCompanylInfo> findAllByCompanyIdJdbc(Long companyId) {
        return jdbcTemplate.query(FIND_ALL_BY_COMPANY_ID, rowMapper, companyId);
    }

    @Override
    public void updateCompanyAndRole(List<User> users) {
        List<Object[]> arguments = users.stream()
                .map(user1 -> new Object[]{user1.getCompany().getId(), user1.getRole().name(), user1.getId()})
                .toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, arguments);
    }

    @Override
    public void updateCompanyAndRoleNamed(List<User> users) {
        MapSqlParameterSource[] arguments = users.stream()
                .map(user1 -> Map.of(
                        "companyId", user1.getCompany().getId(),
                        "role", user1.getRole(),
                        "id", user1.getId()
                ))
                .map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(NAMED_UPDATE_COMPANY_AND_ROLE, arguments);
    }
}
