package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.EmployerService;
import com.kodio.hrms.business.requests.EmployerRequest;
import com.kodio.hrms.business.requests.UpdateEmployerRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.business.responses.EmployerResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.core.utils.Encryptor;
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
	
	private String verifyUrl = "http://localhost:8080/api/user";

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
			
			baseMailSender.sendVerificationEmail(employer, verifyUrl);

			return new SuccessDataResult<EmployerRequest>(employersRequests, "Employer added");

		}

	}

	@Override
	public Result update(Long id, UpdateEmployerRequest updateEmployerRequest) {
		
		Employer employer = employerRepository.findById(id).get();
		
		Result result = baseUserManager.update(id, updateEmployerRequest);
		
		if(!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		if(updateEmployerRequest.getPassword() != null && !updateEmployerRequest.getPassword().isEmpty()) {
			
			String md5NewPassword = Encryptor.encryptPass(updateEmployerRequest.getPassword());
			employer.setPassword(md5NewPassword);
		}
		
		if(updateEmployerRequest.getUsername() != null && !updateEmployerRequest.getUsername().isEmpty()) {
			employer.setUsername(updateEmployerRequest.getUsername());
		}
		
		if(updateEmployerRequest.getEmail() != null && !updateEmployerRequest.getEmail().isEmpty()) {
			
			employer.setEmail(updateEmployerRequest.getEmail());
			
			try {
				baseMailSender.sendVerificationEmail(employer, verifyUrl);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
		}
				
		employerRepository.save(employer);
		
		EmployerResponse employerResponse = EmployerResponse.builder()
				.email(employer.getEmail())
				.username(employer.getUsername())
				.website(employer.getWebsite())
				.phone(employer.getPhone())
				.company(employer.getCompany())
				.build();
		
		return new SuccessDataResult<EmployerResponse>(employerResponse, "Employer is edited");
		
	}

	@Override
	public Result delete(Long id) {

		employerRepository.deleteById(id);

		return new SuccessResult("Employer deleted");
	}

	@Override
	public DataResult<List<EmployerResponse>> getAll() {
		
		List<Employer> employers = employerRepository.findAll();
		List<EmployerResponse> employerResponses = new ArrayList<>();
		
		for (Employer employer : employers) {
			
			EmployerResponse employerResponse = EmployerResponse.builder()
					.email(employer.getEmail())
					.username(employer.getUsername())
					.website(employer.getWebsite())
					.phone(employer.getPhone())
					.company(employer.getCompany())
					.build();
			
			employerResponses.add(employerResponse);
		}
		
		return new SuccessDataResult<List<EmployerResponse>>(employerResponses, "Employers listed");
	}

}
