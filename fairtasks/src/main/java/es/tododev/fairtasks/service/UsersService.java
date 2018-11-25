package es.tododev.fairtasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.User;
import es.tododev.fairtasks.repository.UsersRepository;

@Service
public class UsersService {
	
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
	
}
