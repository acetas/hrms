package com.kodio.hrms.webApi.controllers;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.EmployerService;
import com.kodio.hrms.business.requests.EmployerRequest;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employer/")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody EmployerRequest addEmployersRequests) throws MalformedURLException, UnsupportedEncodingException, MessagingException {
		return ResponseEntity.ok(employerService.add(addEmployersRequests));
	}
	
}
