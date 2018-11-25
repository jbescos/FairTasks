package es.tododev.fairtasks.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tododev.fairtasks.dto.Group;
import es.tododev.fairtasks.service.GroupsService;

@RestController
@RequestMapping("/groups")
public class GroupsController {
	
	private final static Logger log = LogManager.getLogger();
	private final GroupsService groupService;
	
	@Autowired
	public GroupsController(GroupsService groupService) {
		this.groupService = groupService;
	}

	@GetMapping(produces="application/json")
    public List<Group> get(@AuthenticationPrincipal User user) {
		List<Group> groups = groupService.findByUserName(user.getUsername());
		log.debug("Groups {}", groups);
		return groups;
    }
	
	@PostMapping(consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Group> insert(@AuthenticationPrincipal User user, @RequestBody @Valid Group group){
		groupService.insert(group, user.getUsername());
		return ResponseEntity.ok(group);
	}
	
	@DeleteMapping(value="/{groupName}", consumes="application/json", produces="application/json")
	public ResponseEntity<String> delete(@PathVariable @NotNull String groupName) {
		groupService.delete(groupName);
		return ResponseEntity.ok(groupName);
	}
	
}
