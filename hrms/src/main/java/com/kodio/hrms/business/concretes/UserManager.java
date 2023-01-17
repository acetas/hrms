package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.UserService;
import com.kodio.hrms.business.requests.AddUserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.Encryptor;
import com.kodio.hrms.core.validators.UserValidator;
import com.kodio.hrms.dataAccess.abstracts.UserRepository;
import com.kodio.hrms.entities.concretes.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserManager implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private JavaMailSender mailSender;

	@Override
	public DataResult<AddUserRequest> add(AddUserRequest addUserRequest) throws UnsupportedEncodingException, MessagingException {
	
		if(userRepository.existsByUsername(addUserRequest.getUsername())) {
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "Username already exists");
		
		}else if(userRepository.existsByEmail(addUserRequest.getEmail())) {
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "This email is already in use");
		
		}else if(!UserValidator.isValidEmail(addUserRequest)){
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "Invalid email. Try again");
		
		}else if(addUserRequest.getUsername().isEmpty()){
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "Username cannot be empty");
			
		}else if(addUserRequest.getPassword().isEmpty()){
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "Password cannot be empty");
			
		}else if(!addUserRequest.getPassword().equals(addUserRequest.getRePassword())) {
			
			return new ErrorDataResult<AddUserRequest>(addUserRequest, "Your password does not match. Try again");
		
		}else {
			
			String md5Password = Encryptor.encryptPass(addUserRequest.getPassword());
			
			User user = User.builder()
					.email(addUserRequest.getEmail())
					.password(md5Password)
					.username(addUserRequest.getUsername())
					.build();
			
			userRepository.save(user);
			
			sendVerificationEmail(user, "http://localhost:8080/api/user");
			
			return new SuccessDataResult<AddUserRequest>(addUserRequest, "User added");
		
		}
	
	}
		
	public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
	    String fromAddress = "hrmsrobot@gmail.com";
	    String senderName = "HRMS Demo";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", user.getUsername());
	    String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
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
