package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.JobAdvertisementService;
import com.kodio.hrms.business.requests.JobAdvertisementRequest;
import com.kodio.hrms.business.requests.UpdateJobAdvertisementRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobadvertisement/")
public class JobAdvertisementController {

	@Autowired
	private JobAdvertisementService jobAdvertisementService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody JobAdvertisementRequest jobAdvertisementRequest){
		return ResponseEntity.ok(jobAdvertisementService.add(jobAdvertisementRequest));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(int id, UpdateJobAdvertisementRequest updateJobAdvertisementRequest){
		return ResponseEntity.ok(jobAdvertisementService.update(id, updateJobAdvertisementRequest));
	}
	
	@GetMapping("getAllEnabled")
	public ResponseEntity<?> getAllEnabled(){
		return ResponseEntity.ok(jobAdvertisementService.getAllEnabledJobAdvertisement());
	}
	
	@GetMapping("getAllEnabledByAsc")
	public ResponseEntity<?> getAllEnabledByAsc(){
		return ResponseEntity.ok(jobAdvertisementService.getAllEnabledJobAdvertisement());
	}
	
	@GetMapping("getAllEnabledByDesc")
	public ResponseEntity<?> getAllEnabledByDesc(){
		return ResponseEntity.ok(jobAdvertisementService.getAllEnabledJobAdvertisement());
	}
	
	@DeleteMapping("deleteById")
	public ResponseEntity<?> delete(int id){
		return ResponseEntity.ok(jobAdvertisementService.delete(id));
	}
	
	
	@GetMapping("getByCompany")
	public ResponseEntity<?> getAllJobAdvForCompany(@RequestParam int id){
		return ResponseEntity.ok(jobAdvertisementService.getAllJobAdvForCompany(id));
	}
		
}
