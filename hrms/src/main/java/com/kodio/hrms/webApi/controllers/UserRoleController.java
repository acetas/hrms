package com.kodio.hrms.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.UserRoleService;
import com.kodio.hrms.entities.concretes.UserRole;

@RestController
@RequestMapping("/api/userrole/")
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	@PostMapping("add")
	public ResponseEntity<?> add(UserRole userRole){
		return ResponseEntity.ok(userRoleService.add(userRole));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(int id, UserRole userRole){
		return ResponseEntity.ok(userRoleService.update(id, userRole));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(userRoleService.getAll());
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> delete(int id){
		return ResponseEntity.ok(userRoleService.delete(id));
	}
	
}
