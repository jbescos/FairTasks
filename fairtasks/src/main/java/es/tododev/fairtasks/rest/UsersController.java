package es.tododev.fairtasks.rest;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tododev.fairtasks.dto.User;
import es.tododev.fairtasks.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private final UsersService usersService;
	
	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping("/{email}")
    public User get(@PathVariable @Email String email) {
        return usersService.findByEmail(email);
    }
	
	@PostMapping(consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<String> insert(@RequestBody @Valid User user){
		usersService.insert(user);
		return ResponseEntity.ok(user.getEmail());
	}
	
	@DeleteMapping(value="/{email}", consumes="application/json", produces="application/json")
	public ResponseEntity<String> delete(@PathVariable @Email String email) {
		usersService.delete(email);
		return ResponseEntity.ok(email);
	}
	
	@PutMapping(value="/{email}", consumes="application/json", produces="application/json")
	public ResponseEntity<User> update(@PathVariable @Email String email, @RequestBody @Valid User user) {
		usersService.update(email, user);
		return ResponseEntity.ok(user);
	}
	
}
