package es.tododev.fairtasks.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.TaskHistory;
@Repository
public class TasksHistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TasksHistoryRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public List<TaskHistory> findByEmailGroup(String email, String groupName) {
        return jdbcTemplate.query("SELECT * FROM TASKS_HISTORY WHERE EMAIL = ? AND GROUP_NAME = ? ORDER BY DATE DESC", new Object[]{email, groupName}, new TaskHistoryMapper());
    }
    public List<TaskHistory> findByGroup(String groupName) {
        return jdbcTemplate.query("SELECT * FROM TASKS_HISTORY WHERE GROUP_NAME = ? ORDER BY DATE DESC", new Object[]{groupName}, new TaskHistoryMapper());
    }

	public void insert(TaskHistory taskHistory) {
		jdbcTemplate.update("INSERT INTO TASKS_HISTORY (TASK, GROUP_NAME, EMAIL, DATE) VALUES (?, ?, ?, ?)", new Object[] {taskHistory.getTask(), taskHistory.getGroup(), taskHistory.getEmail(), taskHistory.getDate()});
	}
	
	private static class TaskHistoryMapper implements RowMapper<TaskHistory> {
		@Override
		public TaskHistory mapRow(ResultSet arg0, int arg1) throws SQLException {
			return new TaskHistory(arg0.getString("TASK"), arg0.getString("GROUP_NAME"), arg0.getString("EMAIL"), arg0.getDate("DATE"));
		}
	}
	
}
