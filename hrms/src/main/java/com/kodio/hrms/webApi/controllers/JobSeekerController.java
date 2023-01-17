package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobSeekerService;
import com.kodio.hrms.business.requests.JobSeekerRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobseeker/")
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobSeekerRequest addJobSeekerRequest) {
		return ResponseEntity.ok(jobSeekerService.add(addJobSeekerRequest));
	}

}
