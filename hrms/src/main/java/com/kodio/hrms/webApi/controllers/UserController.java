package com.kodio.hrms.webApi.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.UserService;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.SuccessResult;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/user/")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody UserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException {
		return ResponseEntity.ok(userService.add(addUserRequest));
	}
	
	@GetMapping("verify")
	public ResponseEntity<?> verifyUser(@Param("code") String code) {
		boolean verified = userService.verify(code);
		if(verified) {
			return ResponseEntity.ok(new SuccessResult("Your e-mail address has been verified."));
		} else {
			return ResponseEntity.ok(new ErrorResult("Your e-mail address has not been verified."));
		}
	}
	
}
