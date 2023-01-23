package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.TechnologyService;
import com.kodio.hrms.entities.concretes.Technology;

@RestController
@RequestMapping("/api/technology/")
public class TechnologyController {

	@Autowired
	private TechnologyService technologyService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(Technology technology){
		return ResponseEntity.ok(technologyService.add(technology));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(int id, Technology technology){
		return ResponseEntity.ok(technologyService.update(id, technology));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(technologyService.getAll());
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(int id){
		return ResponseEntity.ok(technologyService.delete(id));
	}
	
}
