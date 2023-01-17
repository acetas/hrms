package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.CompanyService;
import com.kodio.hrms.entities.concretes.Company;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/company/")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody Company company){
		return ResponseEntity.ok(companyService.add(company));
	}
	
}
