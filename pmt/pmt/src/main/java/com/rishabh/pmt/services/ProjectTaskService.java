package com.rishabh.pmt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.pmt.domain.Backlog;
import com.rishabh.pmt.domain.Project;
import com.rishabh.pmt.domain.ProjectTask;
import com.rishabh.pmt.exceptions.ProjectNotFoundException;
import com.rishabh.pmt.repositories.BacklogRepository;
import com.rishabh.pmt.repositories.ProjectRepository;
import com.rishabh.pmt.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	ProjectTaskRepository projectTaskRepository;

	@Autowired
	BacklogRepository backlogRepository;
	
	@Autowired
	ProjectRepository projectRepository;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		// Exceptions: Project not found
		// PTs to be added to a specific project, project != null, BL exists
		try {
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

			// set the bl to pt
			projectTask.setBacklog(backlog);
			// we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
			Integer backlogSequence = backlog.getpTSequence();
			// Update the BL SEQUENCE
			backlogSequence++;
			backlog.setpTSequence(backlogSequence);
			// Add Sequence to Project Task
			projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);

			// INITIAL priority when priority null
			if (projectTask.getPriority() == null) {
				projectTask.setPriority(3);
			}
			// INITIAL status when status is null
			if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TO_DO");
			}

			return projectTaskRepository.save(projectTask);

		} catch (Exception ex) {
			throw new ProjectNotFoundException("Project not Found");
		}
	}

	public List<ProjectTask> findBacklogById(String backlogId) {
		
		Project project = projectRepository.findByProjectIdentifier(backlogId);
		if(project == null) {
			throw new ProjectNotFoundException("Project with ID - "+backlogId+" not found");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
	}
	
	public ProjectTask findPTByProjectSequence(String backlogId, String projectSequence) {
		
		//Check if backlog exists, project task exists and task belongs to correct project
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project with id : "+ backlogId +" doesnot exist");
		}
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project Task : "+ projectSequence +" not found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlogId)) {
			throw new ProjectNotFoundException("Project Task "+ projectSequence +" doesnot exist in project "+ backlogId +".");
		}
		
		return projectTask;
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String ptId) {
		
		ProjectTask projectTask = findPTByProjectSequence(backlogId, ptId);
		
		projectTask = updatedTask;
		
		return projectTaskRepository.save(projectTask);
		
	}

	public void deleteByProjectSequence(String backlogId, String ptId) {
		ProjectTask projectTask = findPTByProjectSequence(backlogId, ptId);
		
		projectTaskRepository.delete(projectTask);
	}
}
