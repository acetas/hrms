package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.JobSeekerService;
import com.kodio.hrms.business.requests.AddJobSeekerRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.validators.UserValidator;
import com.kodio.hrms.dataAccess.abstracts.JobSeekerRepository;
import com.kodio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	public DataResult<AddJobSeekerRequest> add(AddJobSeekerRequest addJobSeekerRequest) {

		//TODO don't repeat yourself principle için çözüm !?
		if (jobSeekerRepository.existsByUsername(addJobSeekerRequest.getUsername())) {
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Username already exists");
		
		} else if (jobSeekerRepository.existsByEmail(addJobSeekerRequest.getEmail())) {
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "This email is already in use");
		
		}else if(!UserValidator.isValidEmail(addJobSeekerRequest)){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Invalid email. Try again");
		
		}else if(addJobSeekerRequest.getUsername().isEmpty()){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Username cannot be empty");
			
		}else if(addJobSeekerRequest.getPassword().isEmpty()){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Password cannot be empty");
			
		}else if(!addJobSeekerRequest.getPassword().equals(addJobSeekerRequest.getRePassword())) {
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Your password does not match. Try again");
		
		}else if(addJobSeekerRequest.getName().isEmpty()){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Name field cannot be empty");
			
		}else if(addJobSeekerRequest.getSurName().isEmpty()){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "Surname field cannot be empty");
			
		} else if(addJobSeekerRequest.getNationalIdentity().isEmpty()){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "National id field cannot be empty");
			
		} else if(jobSeekerRepository.existsByNationalIdentity(addJobSeekerRequest.getNationalIdentity())){
			
			return new ErrorDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "National id is already in use");
			
		}else {
			
			JobSeeker jobSeeker = JobSeeker.builder().email(addJobSeekerRequest.getEmail())
					.username(addJobSeekerRequest.getUsername())
					.password(addJobSeekerRequest.getPassword())
					.name(addJobSeekerRequest.getName())
					.surName(addJobSeekerRequest.getSurName())
					.nationalIdentity(addJobSeekerRequest.getNationalIdentity())
					.birthDate(addJobSeekerRequest.getBirthDate())
					.adminEnabled(true)
					.build();

			jobSeekerRepository.save(jobSeeker);
			
			return new SuccessDataResult<AddJobSeekerRequest>(addJobSeekerRequest, "JobSeeker added");
		}

	}

}
