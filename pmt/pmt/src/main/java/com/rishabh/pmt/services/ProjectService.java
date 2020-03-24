package com.rishabh.pmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.pmt.domain.Project;
import com.rishabh.pmt.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		return projectRepository.save(project);
	} 
	
	
}
