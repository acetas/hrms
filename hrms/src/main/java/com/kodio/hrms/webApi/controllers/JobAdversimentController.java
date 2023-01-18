package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobAdversimentService;
import com.kodio.hrms.business.requests.JobAdversimentRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobadversiment/")
public class JobAdversimentController {

	@Autowired
	private JobAdversimentService jobAdversimentService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdversimentRequest jobAdversimentRequest){
		return ResponseEntity.ok(jobAdversimentService.add(jobAdversimentRequest));
	}
	
	@GetMapping("getAllEnabled")
	public ResponseEntity<?> getAllEnabled(){
		return ResponseEntity.ok(jobAdversimentService.getAllEnabledJobAdversiment());
	}
	
	@GetMapping("getAllEnabledByAsc")
	public ResponseEntity<?> getAllEnabledByAsc(){
		return ResponseEntity.ok(jobAdversimentService.getAllEnabledByListingDateAsc());
	}
	
	@GetMapping("getAllEnabledByDesc")
	public ResponseEntity<?> getAllEnabledByDesc(){
		return ResponseEntity.ok(jobAdversimentService.getAllEnabledByListingDateDesc());
	}
		
}
