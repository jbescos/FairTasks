package es.tododev.fairtasks.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tododev.fairtasks.dto.Task;
import es.tododev.fairtasks.service.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {
	
	private final TasksService tasksService;
	
	@Autowired
	public TasksController(TasksService tasksService) {
		this.tasksService = tasksService;
	}

	@GetMapping(value="/{groupName}", produces="application/json")
    public List<Task> get(@PathVariable @NotNull String groupName) {
        return tasksService.findByGroup(groupName);
    }
	
	@PostMapping(consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<String> insert(@RequestBody @Valid Task task){
		tasksService.insert(task);
		return ResponseEntity.ok(task.getTask());
	}
	
	@DeleteMapping(value="/{groupName}/{task}", consumes="application/json", produces="application/json")
	public ResponseEntity<String> delete(@PathVariable @NotNull String groupName, @PathVariable @NotNull String task) {
		tasksService.delete(task, groupName);
		return ResponseEntity.ok(task);
	}
	
}
