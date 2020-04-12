package com.rishabh.pmt.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.pmt.domain.ProjectTask;
import com.rishabh.pmt.services.ErrorValidationService;
import com.rishabh.pmt.services.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	ProjectTaskService projectTaskService;
	
	@Autowired
	ErrorValidationService errorValidationService;

	@PostMapping("/{backlogId}")
	public ResponseEntity<?> addPtToBacklog(@Valid @RequestBody ProjectTask projectTask,
			BindingResult result, @PathVariable String backlogId  ){
		
		ResponseEntity<?> errorMap = errorValidationService.errorValidation(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlogId}")
	public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable String backlogId){
		return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(backlogId), HttpStatus.OK);
	}
	
	@GetMapping("/{backlogId}/{ptId}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlogId, @PathVariable String ptId){
		ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlogId,ptId);
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	@PutMapping("/{backlogId}/{ptId}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
			@PathVariable String backlogId, @PathVariable String ptId){
		
		ResponseEntity<?> errorMap = errorValidationService.errorValidation(result);
		if(errorMap != null) {
			return errorMap;
		}
		
		ProjectTask projectTask1 = projectTaskService.updateByProjectSequence(projectTask, backlogId, ptId);
		return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlogId}/{ptId}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlogId, @PathVariable String ptId){
		
		projectTaskService.deleteByProjectSequence(backlogId, ptId);
		return new ResponseEntity<String>("Project Task "+ ptId +" was successfully deleted", HttpStatus.OK);
	}

}
