package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.UserService;
import com.kodio.hrms.business.requests.UpdateUserRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.business.responses.UserResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.core.utils.Encryptor;
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
	
	private String verifyUrl = "http://localhost:8080/api/user";

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
			baseMailSender.sendVerificationEmail(user, verifyUrl);
			
			return new SuccessDataResult<UserRequest>(addUserRequest, "User added");
		
		}
	
	}
	
	@Override
	public Result update(Long id, UpdateUserRequest updateUserRequest) {
		
		User user = userRepository.findById(id).get();
		
		Result result = baseUserManager.update(id, updateUserRequest);
		
		if(!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		if(updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
			
			String md5NewPassword = Encryptor.encryptPass(updateUserRequest.getPassword());
			user.setPassword(md5NewPassword);
		}
		
		if(updateUserRequest.getUsername() != null && !updateUserRequest.getUsername().isEmpty()) {
			user.setUsername(updateUserRequest.getUsername());
		}
		
		if(updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().isEmpty()) {
			
			user.setEmail(updateUserRequest.getEmail());
			
			try {
				baseMailSender.sendVerificationEmail(user, verifyUrl);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
		}
				
		userRepository.save(user);
		
		UserResponse userResponse = UserResponse.builder()
				.email(user.getEmail())
				.username(user.getUsername())
				.build();
		
		return new SuccessDataResult<UserResponse>(userResponse, "User is edited");
		
	}

	@Override
	public Result delete(Long id) {
		userRepository.deleteById(id);
		return new SuccessResult("User deleted");
	}

	@Override
	public DataResult<List<UserResponse>> getAll() {
		
		List<User> users = userRepository.findAll();
		List<UserResponse> getAllUsersResponse = new ArrayList<>();
		
		for (User user : users) {
			UserResponse userResponse = UserResponse.builder()
					.id(user.getId())
					.email(user.getEmail())
					.username(user.getUsername())
					.mailEnabled(user.isMailEnabled())
					.adminEnabled(user.isAdminEnabled())
					.build();
			getAllUsersResponse.add(userResponse);
		}
		
		return new SuccessDataResult<List<UserResponse>>(getAllUsersResponse, "Users listed");
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
