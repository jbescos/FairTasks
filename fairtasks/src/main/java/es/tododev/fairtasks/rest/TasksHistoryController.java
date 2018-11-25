package es.tododev.fairtasks.rest;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tododev.fairtasks.dto.TaskHistory;
import es.tododev.fairtasks.service.TasksHistoryService;

@RestController
@RequestMapping("/tasksHistory")
public class TasksHistoryController {
	
	private final TasksHistoryService tasksHistoryService;
	
	@Autowired
	public TasksHistoryController(TasksHistoryService tasksHistoryService) {
		this.tasksHistoryService = tasksHistoryService;
	}

	@GetMapping(value="/user/{groupName}", produces="application/json")
    public List<TaskHistory> get(@AuthenticationPrincipal User user, @PathVariable @NotNull String groupName) {
        return tasksHistoryService.findByEmailGroup(user.getUsername(), groupName);
    }
	
	@GetMapping(value="/{groupName}", produces="application/json")
    public List<TaskHistory> get(@PathVariable @NotNull String groupName) {
        return tasksHistoryService.findByGroup(groupName);
    }
	
	@PostMapping(consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<String> insert(@AuthenticationPrincipal User user, @RequestBody @Valid TaskHistory taskHistory){
		taskHistory.setEmail(user.getUsername());
		taskHistory.setDate(new Date());
		tasksHistoryService.insert(taskHistory);
		return ResponseEntity.ok(taskHistory.getTask());
	}
	
}
