package com.kodio.hrms.business.concretes;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.EmployerService;
import com.kodio.hrms.business.requests.EmployersRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.Encryptor;
import com.kodio.hrms.core.validators.EmployerValidator;
import com.kodio.hrms.dataAccess.abstracts.EmployerRepository;
import com.kodio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	@Autowired
	private EmployerRepository employerRepository;

	@Override
	public DataResult<EmployersRequest> add(EmployersRequest addEmployersRequests)
			throws MalformedURLException {

		if(addEmployersRequests.getWebsite().isEmpty()){
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Website cannot be empty");
			
		} else if (!EmployerValidator.isValidCompanyEmail(addEmployersRequests)) {
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Email did not match with website");
		
		} else if(employerRepository.existsByUsername(addEmployersRequests.getUsername())) {
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Username already exists");
		
		}else if(employerRepository.existsByEmail(addEmployersRequests.getEmail())) {
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "This email is already in use");
		
		}else if(addEmployersRequests.getUsername().isEmpty()){
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Username cannot be empty");
			
		}else if(addEmployersRequests.getPassword().isEmpty()){
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Password cannot be empty");
			
		}else if(!addEmployersRequests.getPassword().equals(addEmployersRequests.getRePassword())) {
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Your password does not match. Try again");
		
		} else if(addEmployersRequests.getCompanyName().isEmpty()){
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Company Name cannot be empty");
			
		}else if(addEmployersRequests.getPhone().isEmpty()){
			
			return new ErrorDataResult<EmployersRequest>(addEmployersRequests, "Phone cannot be empty");
			
		}else {

			String md5Password = Encryptor.encryptPass(addEmployersRequests.getPassword());
			
			Employer employer = Employer.builder()
					.email(addEmployersRequests.getEmail())
					.username(addEmployersRequests.getUsername())
					.password(md5Password)
					.companyName(addEmployersRequests.getCompanyName())
					.website(addEmployersRequests.getWebsite())
					.phone(addEmployersRequests.getPhone())
					.build();

			employerRepository.save(employer);

			return new SuccessDataResult<EmployersRequest>(addEmployersRequests, "Employer added");

		}

	}

}
