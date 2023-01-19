package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CandidateService;
import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.utils.mailSender.BaseMailSender;
import com.kodio.hrms.dataAccess.abstracts.CandidateRepository;
import com.kodio.hrms.entities.concretes.Candidate;

import jakarta.mail.MessagingException;

@Service
public class CandidateManager implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private BaseUserManager baseUserManager;
	
	@Autowired
    private BaseMailSender baseMailSender;

	public DataResult<CandidateRequest> add(CandidateRequest candidateRequest) throws UnsupportedEncodingException, MessagingException {
			
		DataResult<UserRequest> result = baseUserManager.add(candidateRequest);
		candidateRequest.setPassword(result.getData().getPassword());
		
		if(result.isSuccess() == false) {
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, result.getMessage());
			
		} else if(candidateRequest.getName().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Name field cannot be empty");
			
		} else if(candidateRequest.getSurName().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "Surname field cannot be empty");
			
		} else if(candidateRequest.getNationalIdentity().isEmpty()){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "National id field cannot be empty");
			
		} else if(candidateRepository.existsByNationalIdentity(candidateRequest.getNationalIdentity())){
			
			return new ErrorDataResult<CandidateRequest>(candidateRequest, "National id is already in use");
			
		} else {
			
			Candidate candidate = Candidate.builder().email(candidateRequest.getEmail())
					.username(candidateRequest.getUsername())
					.password(candidateRequest.getPassword())
					.name(candidateRequest.getName())
					.surName(candidateRequest.getSurName())
					.nationalIdentity(candidateRequest.getNationalIdentity())
					.birthDate(candidateRequest.getBirthDate())
					.adminEnabled(true)
					.build();

			candidateRepository.save(candidate);
			
			baseMailSender.sendVerificationEmail(candidate, "http://localhost:8080/api/user");
			
			return new SuccessDataResult<CandidateRequest>(candidateRequest, "Candidate added");
		}

	}

}
