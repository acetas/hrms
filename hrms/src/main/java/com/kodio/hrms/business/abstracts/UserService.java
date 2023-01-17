package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;

import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.entities.concretes.User;

import jakarta.mail.MessagingException;

public interface UserService {

	DataResult<UserRequest> add(UserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException;
	
	void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException;
	
	boolean verify(String verificationCode);
	
}
