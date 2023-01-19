package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CandidateService;
import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.validators.UserValidator;
import com.kodio.hrms.dataAccess.abstracts.CandidateRepository;
import com.kodio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	public DataResult<CandidateRequest> add(CandidateRequest candidateRequest) {

		//TODO don't repeat yourself principle için çözüm !?
		if (candidateRepository.existsByUsername(candidateRequest.getUsername())) {
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Username already exists");
		
		} else if (candidateRepository.existsByEmail(candidateRequest.getEmail())) {
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "This email is already in use");
		
		}else if(!UserValidator.isValidEmail(candidateRequest)){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Invalid email. Try again");
		
		}else if(candidateRequest.getUsername().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Username cannot be empty");
			
		}else if(candidateRequest.getPassword().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Password cannot be empty");
			
		}else if(!candidateRequest.getPassword().equals(candidateRequest.getRePassword())) {
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Your password does not match. Try again");
		
		}else if(candidateRequest.getName().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Name field cannot be empty");
			
		}else if(candidateRequest.getSurName().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Surname field cannot be empty");
			
		} else if(candidateRequest.getNationalIdentity().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "National id field cannot be empty");
			
		} else if(candidateRepository.existsByNationalIdentity(candidateRequest.getNationalIdentity())){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "National id is already in use");
			
		}else {
			
			Candidate jobSeeker = Candidate.builder().email(candidateRequest.getEmail())
					.username(candidateRequest.getUsername())
					.password(candidateRequest.getPassword())
					.name(candidateRequest.getName())
					.surName(candidateRequest.getSurName())
					.nationalIdentity(candidateRequest.getNationalIdentity())
					.birthDate(candidateRequest.getBirthDate())
					.adminEnabled(true)
					.build();

			candidateRepository.save(jobSeeker);
			
			return new SuccessDataResult<CandidateRequest>(candidateRequest, "Candidate added");
		}

	}

}
