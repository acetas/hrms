package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.requests.UpdateUserRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.core.utils.Encryptor;
import com.kodio.hrms.core.validators.UserValidator;
import com.kodio.hrms.dataAccess.abstracts.UserRepository;
import com.kodio.hrms.entities.concretes.User;

import jakarta.mail.MessagingException;

@Service
public class BaseUserManager {

	@Autowired
	private UserRepository userRepository;

	public DataResult<UserRequest> add(UserRequest userRequest)
			throws UnsupportedEncodingException, MessagingException {

		if (userRepository.existsByUsername(userRequest.getUsername())) {

			return new ErrorDataResult<UserRequest>(userRequest, "Username already exists");

		} else if (userRepository.existsByEmail(userRequest.getEmail())) {

			return new ErrorDataResult<UserRequest>(userRequest, "This email is already in use");

		} else if (!UserValidator.isValidEmail(userRequest)) {

			return new ErrorDataResult<UserRequest>(userRequest, "Invalid email. Try again");

		} else if (userRequest.getUsername().isEmpty()) {

			return new ErrorDataResult<UserRequest>(userRequest, "Username cannot be empty");

		} else if (userRequest.getPassword().isEmpty()) {

			return new ErrorDataResult<UserRequest>(userRequest, "Password cannot be empty");

		} else if (!userRequest.getPassword().equals(userRequest.getRePassword())) {

			return new ErrorDataResult<UserRequest>(userRequest, "Your password does not match. Try again");

		} else {

			String md5Password = Encryptor.encryptPass(userRequest.getPassword());
			userRequest.setPassword(md5Password);

			return new SuccessDataResult<UserRequest>(userRequest);

		}

	}

	public Result update(Long id, UpdateUserRequest updateUserRequest) {

		User user = userRepository.findById(id).get();

		if (user.getUsername().equals(updateUserRequest.getUsername())) {
			return new ErrorResult("You are already using this username");
		} else if (userRepository.existsByUsername(updateUserRequest.getUsername())) {
			return new ErrorResult("This username is not available");
		}

		if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
			
			String md5Password = Encryptor.encryptPass(updateUserRequest.getOldPassword());

			if (updateUserRequest.getPassword() != md5Password) {
				return new ErrorResult("Passwords do not match");
			}
		}

		if (user.getEmail() == updateUserRequest.getEmail()) {
			return new ErrorResult("You are already using this email");
		} else if (userRepository.existsByEmail(updateUserRequest.getEmail())) {
			return new ErrorResult("This email is already in use");
		}

		return new SuccessResult();
	}

}
