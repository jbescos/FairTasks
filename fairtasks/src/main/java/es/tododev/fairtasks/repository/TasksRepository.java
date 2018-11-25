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
import es.tododev.fairtasks.dto.Task;
@Repository
public class TasksRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TasksRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(readOnly=true)
    public List<Task> findByGroup(String groupName) {
        return jdbcTemplate.query("SELECT * FROM TASKS WHERE GROUP_NAME = ?", new Object[]{groupName}, new TaskMapper());
    }

	@Transactional
	public void insert(Task task) {
		jdbcTemplate.update("INSERT INTO TASKS (TASK, GROUP_NAME, VALUE, DESCRIPTION) VALUES (?, ?, ?, ?)", new Object[] {task.getTask(), task.getGroup(), task.getValue(), task.getDescription()});
	}
	
	@Transactional
	public void delete(String task, String groupName) {
		jdbcTemplate.update("DELETE FROM TASKS WHERE TASK = ?", new Object[] {task});
		jdbcTemplate.update("DELETE FROM TASKS_HISTORY WHERE TASK = ? AND GROUP_NAME = ?", new Object[] {task, groupName});
	}
	
	private static class TaskMapper implements RowMapper<Task> {
		@Override
		public Task mapRow(ResultSet arg0, int arg1) throws SQLException {
			return new Task(arg0.getString("TASK"), arg0.getString("GROUP_NAME"), arg0.getInt("VALUE"), arg0.getString("DESCRIPTION"));
		}
	}
	
}
