package es.tododev.fairtasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tododev.fairtasks.dto.Task;
import es.tododev.fairtasks.dto.User;
import es.tododev.fairtasks.repository.TasksRepository;
import es.tododev.fairtasks.repository.UsersRepository;

@Service
public class TasksService {
	
	private final TasksRepository tasksRepository;

	@Autowired
	public TasksService(TasksRepository tasksRepository) {
		this.tasksRepository = tasksRepository;
	}
	
	public List<Task> findByGroup(String groupName) {
		return tasksRepository.findByGroup(groupName);
	}
	
	public void insert(Task task) {
		tasksRepository.insert(task);
	}
	
	public void delete(String task, String groupName) {
		tasksRepository.delete(task, groupName);
	}
	
}
