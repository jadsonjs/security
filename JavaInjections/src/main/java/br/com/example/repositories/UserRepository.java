/*
 * Injections
 * UserRepository
 * @since 14/07/2021
 */
package br.com.example.repositories;

import br.com.example.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * This is the wrong way to use parameters in SQL queries, can generate SQL injection
     * @param login
     * @return
     */
    public User validateLogin(Login login) {
        String sql = "select * from users where username='" + login.username + "' and password='" + login.password + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     * This is the right way of use parameters in sql queries
     * @param login
     * @return
     */
    public User validateLogin2(Login login) {
        String sql = "select * from users where username= ? and password= ? ";
        List<User> users = jdbcTemplate.query(sql, new Object[] { login.username , login.password }, new UserMapper());
        return users.size() > 0 ? users.get(0) : null;
    }

    class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int arg1) throws SQLException {
            User user = null;
            return user;
        }
    }
}
