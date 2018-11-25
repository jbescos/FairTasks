package es.tododev.fairtasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tododev.fairtasks.dto.Task;
import es.tododev.fairtasks.dto.TaskHistory;
import es.tododev.fairtasks.dto.User;
import es.tododev.fairtasks.repository.TasksHistoryRepository;
import es.tododev.fairtasks.repository.TasksRepository;
import es.tododev.fairtasks.repository.UsersRepository;

@Service
public class TasksHistoryService {
	
	private final TasksHistoryRepository tasksHistoryRepository;

	@Autowired
	public TasksHistoryService(TasksHistoryRepository tasksHistoryRepository) {
		this.tasksHistoryRepository = tasksHistoryRepository;
	}
	
	public List<TaskHistory> findByGroup(String groupName) {
		return tasksHistoryRepository.findByGroup(groupName);
	}
	
	public List<TaskHistory> findByEmailGroup(String email, String groupName) {
		return tasksHistoryRepository.findByEmailGroup(email, groupName);
	}
	
	public void insert(TaskHistory tasksHistory) {
		tasksHistoryRepository.insert(tasksHistory);
	}
	
}
