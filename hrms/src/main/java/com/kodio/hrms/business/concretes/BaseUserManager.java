package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.Encryptor;
import com.kodio.hrms.core.validators.UserValidator;
import com.kodio.hrms.dataAccess.abstracts.UserRepository;

import jakarta.mail.MessagingException;

public class BaseUserManager {

	@Autowired
	private UserRepository userRepository;
	
	public DataResult<UserRequest> add(UserRequest userRequest) throws UnsupportedEncodingException, MessagingException{
		
		if(userRepository.existsByUsername(userRequest.getUsername())) {
			
			return new ErrorDataResult<UserRequest>(userRequest, "Username already exists");
		
		}else if(userRepository.existsByEmail(userRequest.getEmail())) {
			
			return new ErrorDataResult<UserRequest>(userRequest, "This email is already in use");
		
		}else if(!UserValidator.isValidEmail(userRequest)){
			
			return new ErrorDataResult<UserRequest>(userRequest, "Invalid email. Try again");
		
		}else if(userRequest.getUsername().isEmpty()){
			
			return new ErrorDataResult<UserRequest>(userRequest, "Username cannot be empty");
			
		}else if(userRequest.getPassword().isEmpty()){
			
			return new ErrorDataResult<UserRequest>(userRequest, "Password cannot be empty");
			
		}else if(!userRequest.getPassword().equals(userRequest.getRePassword())) {
			
			return new ErrorDataResult<UserRequest>(userRequest, "Your password does not match. Try again");
		
		}else {
			
			String md5Password = Encryptor.encryptPass(userRequest.getPassword());
			userRequest.setPassword(md5Password);
						
			return new SuccessDataResult<UserRequest>(userRequest);
		
		}

	}
	
}
