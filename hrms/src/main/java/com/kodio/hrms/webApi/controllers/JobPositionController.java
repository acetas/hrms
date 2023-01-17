package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobPositionService;
import com.kodio.hrms.business.requests.JobPositionRequest;

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
	
}
