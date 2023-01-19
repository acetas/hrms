package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;

import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;

import jakarta.mail.MessagingException;

public interface UserService {

	DataResult<UserRequest> add(UserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException;
	
	boolean verify(String verificationCode);
	
}
