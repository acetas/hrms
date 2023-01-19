package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.CvService;
import com.kodio.hrms.entities.concretes.Cv;

@RestController
@RequestMapping("/api/cv/")
public class CvController {

	@Autowired
	private CvService cvService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody Cv cv){
		return ResponseEntity.ok(cvService.add(cv));
	}
	
}
