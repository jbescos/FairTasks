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
	
	@Transactional
	public void insert(Group group) {
		groupsRepository.insert(group);
	}
	
	@Transactional
	public void delete(String group) {
		groupsRepository.delete(group);
	}
	
}
