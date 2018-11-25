package es.tododev.fairtasks.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.Group;
@Repository
public class GroupsRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GroupsRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(readOnly=true)
    public List<Group> findAll() {
        return jdbcTemplate.query("SELECT * FROM GROUPS", new GroupMapper());
    }
	
	@Transactional(readOnly=true)
    public List<Group> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM GROUPS WHERE EMAIL = ?", new Object[]{email}, new GroupMapper());
    }

	@Transactional
	public void insert(Group group) {
		jdbcTemplate.update("INSERT INTO GROUPS (EMAIL, GROUP_NAME) VALUES (?, ?)", new Object[] {group.getEmail(), group.getGroup()});
	}
	
	@Transactional
	public void delete(String group) {
		jdbcTemplate.update("DELETE FROM GROUPS WHERE GROUP_NAME = ?", new Object[] {group});
		jdbcTemplate.update("DELETE FROM TASKS WHERE GROUP_NAME = ?", new Object[] {group});
		jdbcTemplate.update("DELETE FROM TASKS_HISTORY WHERE GROUP_NAME = ?", new Object[] {group});
	}
	
	private static class GroupMapper implements RowMapper<Group> {
		@Override
		public Group mapRow(ResultSet arg0, int arg1) throws SQLException {
			return new Group(arg0.getString("GROUP_NAME"), arg0.getString("EMAIL"));
		}
	}
	
}
