package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.UserService;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.mailSender.BaseMailSender;
import com.kodio.hrms.dataAccess.abstracts.UserRepository;
import com.kodio.hrms.entities.concretes.User;

import jakarta.mail.MessagingException;

@Service
public class UserManager implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private BaseMailSender baseMailSender;
	
	@Autowired
	private BaseUserManager baseUserManager;

	@Override
	public DataResult<UserRequest> add(UserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException {
	
		DataResult<UserRequest> result = baseUserManager.add(addUserRequest);
		
		if(result.isSuccess() == false){
			
			return new ErrorDataResult<UserRequest>(result.getData(), result.getMessage());
		
		}else {
			
			User user = User.builder()
					.email(result.getData().getEmail())
					.password(result.getData().getPassword())
					.username(result.getData().getUsername())
					.build();
			
			userRepository.save(user);
			baseMailSender.sendVerificationEmail(user, "http://localhost:8080/api/user");
			
			return new SuccessDataResult<UserRequest>(addUserRequest, "User added");
		
		}
	
	}
	
	public boolean verify(String verificationCode) {
		
		User user = userRepository.getByVerificationCode(verificationCode);
		
		if(user == null || user.isMailEnabled()) {
			return false;
		} else {
			user.setMailEnabled(true);
			userRepository.save(user);
			return true;
		}
		
	}
	
}
