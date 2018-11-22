package es.tododev.fairtasks.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tododev.fairtasks.dto.Group;
import es.tododev.fairtasks.service.GroupsService;

@RestController
@RequestMapping("/groups")
public class GroupsController {
	
	private final GroupsService groupService;
	
	@Autowired
	public GroupsController(GroupsService groupService) {
		this.groupService = groupService;
	}

	@GetMapping(produces="application/json")
    public List<Group> get(@AuthenticationPrincipal User user) {
        return groupService.findByUserName(user.getUsername());
    }
	
	@PostMapping(consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<Group> insert(@AuthenticationPrincipal User user, @RequestBody @Valid Group group){
		group.setEmail(user.getUsername());
		groupService.insert(group);
		return ResponseEntity.ok(group);
	}
	
	@DeleteMapping(value="/{group}", consumes="application/json", produces="application/json")
	public ResponseEntity<String> delete(@PathVariable @NotNull String group) {
		groupService.delete(group);
		return ResponseEntity.ok(group);
	}
	
	@PutMapping(value="/{group}", consumes="application/json", produces="application/json")
	public ResponseEntity<Group> update(@PathVariable @NotNull String groupName, @AuthenticationPrincipal User user, @RequestBody @Valid Group group) {
		group.setEmail(user.getUsername());
		groupService.update(groupName, group);
		return ResponseEntity.ok(group);
	}
	
}
