package es.tododev.fairtasks.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.User;
import es.tododev.fairtasks.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {
	
	private final UsersRepository usersRepository;

	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	
	@Transactional
	public void insert(User user) {
		usersRepository.insert(user);
	}
	
	@Transactional
	public void delete(String email) {
		usersRepository.delete(email);
	}

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = usersRepository.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority());
	}
	
	private List<GrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
}
