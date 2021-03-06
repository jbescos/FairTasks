package es.tododev.fairtasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tododev.fairtasks.dto.Group;
import es.tododev.fairtasks.repository.GroupsRepository;

@Service
public class GroupsService {
	
	private final GroupsRepository groupsRepository;
	
	@Autowired
	public GroupsService(GroupsRepository groupsRepository) {
		this.groupsRepository = groupsRepository;
	}
	
	@Transactional(readOnly=true)
	public List<Group> findByUserName(String email) {
		return groupsRepository.findByEmail(email);
	}
	
	@Transactional(readOnly=true)
	public List<Group> findUnique(String email) {
		return groupsRepository.findUnique(email);
	}
	
	@Transactional
	public void insert(Group group, String email) {
		if(group.getEmail() != null) {
			groupsRepository.insert(group);
		}
		List<Group> existing = groupsRepository.findByEmailGroup(email, group.getGroup());
		if(existing.isEmpty()) {
			group.setEmail(email);
			groupsRepository.insert(group);
		}
	}
	
	@Transactional
	public void delete(String group) {
		groupsRepository.delete(group);
	}
	
}
