package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.kodio.hrms.business.requests.UpdateUserRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.business.responses.UserResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

import jakarta.mail.MessagingException;

public interface UserService {

	DataResult<UserRequest> add(UserRequest userRequest) throws UnsupportedEncodingException, MessagingException;
	
	Result update(Long id, UpdateUserRequest updateUserRequest);
	
	Result delete(Long id);
	
	DataResult<List<UserResponse>> getAll();
	
	boolean verify(String verificationCode);
	
}
