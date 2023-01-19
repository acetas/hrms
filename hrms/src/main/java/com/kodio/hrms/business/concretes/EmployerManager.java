package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.EmployerService;
import com.kodio.hrms.business.requests.EmployerRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.mailSender.BaseMailSender;
import com.kodio.hrms.core.validators.EmployerValidator;
import com.kodio.hrms.dataAccess.abstracts.EmployerRepository;
import com.kodio.hrms.entities.concretes.Employer;

import jakarta.mail.MessagingException;

@Service
public class EmployerManager implements EmployerService {

	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private BaseUserManager baseUserManager;
	
	@Autowired
    private BaseMailSender baseMailSender;

	@Override
	public DataResult<EmployerRequest> add(EmployerRequest employersRequests)
			throws MalformedURLException, UnsupportedEncodingException, MessagingException {

		DataResult<UserRequest> result = baseUserManager.add(employersRequests);
		employersRequests.setPassword(result.getData().getPassword());
		
		if(result.isSuccess() == false) {
			
			return new ErrorDataResult<EmployerRequest>(employersRequests, result.getMessage());
			
		} else if(employersRequests.getWebsite().isEmpty()){
			
			return new ErrorDataResult<EmployerRequest>(employersRequests, "Website cannot be empty");
			
		} else if (!EmployerValidator.isValidCompanyEmail(employersRequests)) {
			
			return new ErrorDataResult<EmployerRequest>(employersRequests, "Email did not match with website");
		
		} else if(employersRequests.getPhone().isEmpty()){
			
			return new ErrorDataResult<EmployerRequest>(employersRequests, "Phone cannot be empty");
			
		}else {
			
			Employer employer = Employer.builder()
					.email(employersRequests.getEmail())
					.username(employersRequests.getUsername())
					.password(employersRequests.getPassword())
					.company(employersRequests.getCompany())
					.website(employersRequests.getWebsite())
					.phone(employersRequests.getPhone())
					.build();

			employerRepository.save(employer);
			
			baseMailSender.sendVerificationEmail(employer, "http://localhost:8080/api/user");

			return new SuccessDataResult<EmployerRequest>(employersRequests, "Employer added");

		}

	}

}
