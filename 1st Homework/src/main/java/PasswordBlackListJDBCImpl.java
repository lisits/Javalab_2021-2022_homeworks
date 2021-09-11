import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordBlackListJDBCImpl implements PasswordBlackList {
    private JdbcTemplate jdbcTemplate;
    //language=SQL
    private static final String SQL_SELECT_PASSWORD = "select * from passwords_black_list where password = ?";

    public PasswordBlackListJDBCImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Password> rowMapper = new RowMapper<>() {
        @Override
        public Password mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Password(resultSet.getInt("id"), resultSet.getString("password"));
        }
    };

    @Override
    public boolean contains(String password) {
        if (jdbcTemplate.query(SQL_SELECT_PASSWORD, rowMapper, password).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
