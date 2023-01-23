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

import com.kodio.hrms.business.abstracts.CvService;
import com.kodio.hrms.business.requests.CvRequest;
import com.kodio.hrms.business.requests.UpdateCvRequest;

@RestController
@RequestMapping("/api/cv/")
public class CvController {

	@Autowired
	private CvService cvService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(@RequestBody CvRequest cvRequest){
		return ResponseEntity.ok(cvService.add(cvRequest));
	}
	
	@GetMapping("getByCandidate")
	public ResponseEntity<?> getByCandidate(@RequestParam Long id){
		return ResponseEntity.ok(cvService.getByCandidate(id));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(Long id, UpdateCvRequest updateCvRequest){
		return ResponseEntity.ok(cvService.update(id, updateCvRequest));
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> update(Long id){
		return ResponseEntity.ok(cvService.delete(id));
	}
	
}
