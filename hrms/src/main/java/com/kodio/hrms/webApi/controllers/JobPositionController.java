package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobPositionService;
import com.kodio.hrms.business.requests.JobPositionRequest;
import com.kodio.hrms.business.requests.UpdateJobPositionRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobposition/")
public class JobPositionController {

	@Autowired
	private JobPositionService jobPositionService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobPositionRequest jobPositionRequest){
		return ResponseEntity.ok(jobPositionService.add(jobPositionRequest));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(int id, UpdateJobPositionRequest updateJobPositionRequest){
		return ResponseEntity.ok(jobPositionService.update(id, updateJobPositionRequest));
	}
	
	@DeleteMapping("deleteById")
	public ResponseEntity<?> delete(int id){
		return ResponseEntity.ok(jobPositionService.delete(id));
	}
	
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(jobPositionService.getAll());
	}
	
}
