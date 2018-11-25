package es.tododev.fairtasks.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.Group;
import es.tododev.fairtasks.rest.GroupsController;
@Repository
public class GroupsRepository {

	private final JdbcTemplate jdbcTemplate;
	private final Logger log = LoggerFactory.getLogger(GroupsRepository.class);

	@Autowired
	public GroupsRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    public List<Group> findAll() {
        return jdbcTemplate.query("SELECT * FROM GROUPS ORDER BY GROUP_NAME", new GroupMapper());
    }
	
    public List<Group> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM GROUPS WHERE GROUP_NAME IN (SELECT GROUP_NAME FROM GROUPS WHERE EMAIL = ?) ORDER BY GROUP_NAME", new Object[]{email}, new GroupMapper());
    }
    
    public List<Group> findUnique(String email) {
        return jdbcTemplate.query("SELECT * FROM GROUPS WHERE EMAIL = ? ORDER BY GROUP_NAME", new Object[]{email}, new GroupMapper());
    }
    
    public List<Group> findByEmailGroup(String email, String group) {
        return jdbcTemplate.query("SELECT * FROM GROUPS WHERE EMAIL = ? AND GROUP_NAME = ?", new Object[]{email, group}, new GroupMapper());
    }

	public void insert(Group group) {
		log.debug("Insert {}", group);
		jdbcTemplate.update("INSERT INTO GROUPS (EMAIL, GROUP_NAME) VALUES (?, ?)", new Object[] {group.getEmail(), group.getGroup()});
	}
	
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
