package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.HighSchoolService;
import com.kodio.hrms.entities.concretes.HighSchool;

@RestController
@RequestMapping("/api/highschool")
public class HighSchoolController {

	@Autowired
	private HighSchoolService highSchoolService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(HighSchool highSchool){
		return ResponseEntity.ok(highSchoolService.add(highSchool));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(int id, HighSchool highSchool){
		return ResponseEntity.ok(highSchoolService.update(id, highSchool));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(highSchoolService.getAll());
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(int id){
		return ResponseEntity.ok(highSchoolService.delete(id));
	}
	
}
