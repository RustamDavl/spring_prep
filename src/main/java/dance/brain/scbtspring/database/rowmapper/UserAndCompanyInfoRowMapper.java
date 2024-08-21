package dance.brain.scbtspring.database.rowmapper;

import dance.brain.scbtspring.dto.UserAndCompanylInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAndCompanyInfoRowMapper implements RowMapper<UserAndCompanylInfo> {
    @Override
    public UserAndCompanylInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserAndCompanylInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getLong("companyId"),
                rs.getString("companyName")
        );
    }
}
