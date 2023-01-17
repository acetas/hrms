package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobSeekerService;
import com.kodio.hrms.business.concretes.JobSeekerManager;
import com.kodio.hrms.business.requests.AddJobSeekerRequest;
import com.kodio.hrms.core.results.DataResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobseeker/")
public class JobSeekerController {

	private JobSeekerService jobSeekerService;

	@Autowired
	public JobSeekerController(JobSeekerManager jobSeekerManager) {
		this.jobSeekerService = jobSeekerManager;
	}

	@PostMapping("add")
	public DataResult<AddJobSeekerRequest> add(@Valid @RequestBody AddJobSeekerRequest addJobSeekerRequest) {
		return jobSeekerService.add(addJobSeekerRequest);
	}

}
