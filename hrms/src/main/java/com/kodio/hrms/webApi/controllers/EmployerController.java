package com.kodio.hrms.webApi.controllers;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.EmployerService;
import com.kodio.hrms.business.requests.AddEmployersRequests;
import com.kodio.hrms.core.results.DataResult;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employer/")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	@PostMapping("add")
	public DataResult<AddEmployersRequests> add(@Valid @RequestBody AddEmployersRequests addEmployersRequests) throws MalformedURLException {
		return employerService.add(addEmployersRequests);
	}
	
}
