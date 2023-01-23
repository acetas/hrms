package com.kodio.hrms.webApi.controllers;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.EmployeeService;
import com.kodio.hrms.business.requests.EmployeeRequest;
import com.kodio.hrms.business.requests.UpdateEmployeeRequest;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody EmployeeRequest employereRequests) throws MalformedURLException, UnsupportedEncodingException, MessagingException {
		return ResponseEntity.ok(employeeService.add(employereRequests));
	}
	
	@PutMapping("update")
	public ResponseEntity<?> update(Long id, UpdateEmployeeRequest updateEmployeeRequest){
		return ResponseEntity.ok(employeeService.update(id, updateEmployeeRequest));
	}
	
	@DeleteMapping("deleteById")
	public ResponseEntity<?> delete(Long id){
		return ResponseEntity.ok(employeeService.delete(id));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(employeeService.getAll());
	}
	
}
