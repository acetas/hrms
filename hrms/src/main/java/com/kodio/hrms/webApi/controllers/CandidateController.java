package com.kodio.hrms.webApi.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.CandidateService;
import com.kodio.hrms.business.requests.CandidateRequest;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate/")
public class CandidateController {

	@Autowired
	private CandidateService jobSeekerService;

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody CandidateRequest candidateRequest) throws UnsupportedEncodingException, MessagingException {
		return ResponseEntity.ok(jobSeekerService.add(candidateRequest));
	}

}
