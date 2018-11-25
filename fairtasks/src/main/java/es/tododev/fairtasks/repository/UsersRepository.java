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
	
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM USERS", new UserMapper());
    }
	
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{email}, new UserMapper());
    }
	
    public List<User> findByGroup(String group) {
        return jdbcTemplate.query("SELECT USERS.* FROM USERS INNER JOIN GROUPS ON GROUP = ?", new Object[]{group}, new UserMapper());
    }
	
	public void insert(User user) {
		jdbcTemplate.update("INSERT INTO USERS (EMAIL, USER_NAME) VALUES (?, ?)", new Object[] {user.getEmail(), user.getUserName()});
	}
	
	public void delete(String email) {
		jdbcTemplate.update("DELETE FROM USERS WHERE EMAIL = ?", new Object[] {email});
		jdbcTemplate.update("DELETE FROM GROUPS WHERE EMAIL = ?", new Object[] {email});
		jdbcTemplate.update("DELETE FROM TASKS_HISTORY WHERE EMAIL = ?", new Object[] {email});
	}
	
	private static class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet arg0, int arg1) throws SQLException {
			return new User(arg0.getString("USER_NAME"), arg0.getString("EMAIL"));
		}
	}
	
}
