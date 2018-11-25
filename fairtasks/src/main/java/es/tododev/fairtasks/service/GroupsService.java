package es.tododev.fairtasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.tododev.fairtasks.dto.Group;
import es.tododev.fairtasks.repository.GroupsRepository;

@Service
public class GroupsService {
	
	private final GroupsRepository groupsRepository;
	
	@Autowired
	public GroupsService(GroupsRepository groupsRepository) {
		this.groupsRepository = groupsRepository;
	}
	
	public List<Group> findByUserName(String email) {
		return groupsRepository.findByEmail(email);
	}
	
	public void insert(Group group) {
		groupsRepository.insert(group);
	}
	
	public void delete(String group) {
		groupsRepository.delete(group);
	}
	
}
