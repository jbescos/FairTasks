package es.tododev.fairtasks.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.User;

@Repository
public class UsersRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UsersRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(readOnly=true)
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM USERS", new UserMapper());
    }
	
	@Transactional(readOnly=true)
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{email}, new UserMapper());
    }
	
	@Transactional(readOnly=true)
    public List<User> findByGroup(String group) {
        return jdbcTemplate.query("SELECT USERS.* FROM USERS INNER JOIN GROUPS ON GROUP = ?", new Object[]{group}, new UserMapper());
    }
	
	@Transactional
	public void insert(User user) {
		jdbcTemplate.update("INSERT INTO USERS (EMAIL, USER_NAME) VALUES (?, ?)", new Object[] {user.getEmail(), user.getUserName()});
	}
	
	@Transactional
	public void update(String email, User user) {
		jdbcTemplate.update("UPDATE USERS SET USER_NAME = ? WHERE EMAIL = ?", new Object[] {user.getUserName(), email});
	}
	
	@Transactional
	public void delete(String email) {
		jdbcTemplate.update("DELETE FOM USERS WHERE EMAIL = ?", new Object[] {email});
	}
	
	private static class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet arg0, int arg1) throws SQLException {
			return new User(arg0.getString("USER_NAME"), arg0.getString("EMAIL"));
		}
	}
	
}
