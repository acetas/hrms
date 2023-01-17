package com.kodio.hrms.webApi.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodio.hrms.business.abstracts.UserService;
import com.kodio.hrms.business.requests.AddUserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
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
	public DataResult<AddUserRequest> add(@Valid @RequestBody AddUserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException {
		return userService.add(addUserRequest);
	}
	
	@GetMapping("verify")
	public Result verifyUser(@Param("code") String code) {
		boolean verified = userService.verify(code);
		if(verified) {
			return new SuccessResult("Your e-mail address has been verified.");
		} else {
			return new ErrorResult("Your e-mail address has not been verified.");
		}
	}
	
}
