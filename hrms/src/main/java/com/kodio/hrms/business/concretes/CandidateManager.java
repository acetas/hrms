package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CandidateService;
import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.business.requests.UpdateCandidateRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.business.responses.CandidateResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.core.utils.Encryptor;
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
	
	private String verifyUrl = "http://localhost:8080/api/user";

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

	@Override
	public Result update(Long id, UpdateCandidateRequest updateCandidateRequest) {
		
		Candidate user = candidateRepository.findById(id).get();
		
		Result result = baseUserManager.update(id, updateCandidateRequest);
		
		if(!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		if(updateCandidateRequest.getPassword() != null && !updateCandidateRequest.getPassword().isEmpty()) {
			
			String md5NewPassword = Encryptor.encryptPass(updateCandidateRequest.getPassword());
			user.setPassword(md5NewPassword);
		}
		
		if(updateCandidateRequest.getUsername() != null && !updateCandidateRequest.getUsername().isEmpty()) {
			user.setUsername(updateCandidateRequest.getUsername());
		}
		
		if(updateCandidateRequest.getEmail() != null && !updateCandidateRequest.getEmail().isEmpty()) {
			
			user.setEmail(updateCandidateRequest.getEmail());
			
			try {
				baseMailSender.sendVerificationEmail(user, verifyUrl);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
		}
				
		candidateRepository.save(user);
		
		CandidateResponse candidateResponse = CandidateResponse.builder()
				.email(user.getEmail())
				.username(user.getUsername())
				.name(user.getName())
				.surName(user.getSurName())
				.build();
		
		return new SuccessDataResult<CandidateResponse>(candidateResponse, "Candidate is edited");
	}

	@Override
	public Result delete(Long id) {
		candidateRepository.deleteById(id);
		return new SuccessResult("Candidate deleted");
	}

	@Override
	public DataResult<List<CandidateResponse>> getAll() {
		List<Candidate> candidates = candidateRepository.findAll();
		List<CandidateResponse> candidateResponses = new ArrayList<>();
		
		for (Candidate candidate : candidates) {
			CandidateResponse candidateResponse = CandidateResponse.builder()
					.name(candidate.getName())
					.surName(candidate.getSurName())
					.email(candidate.getEmail())
					.username(candidate.getUsername())
					.build();
			
			candidateResponses.add(candidateResponse);
		}
		return new SuccessDataResult<List<CandidateResponse>>(candidateResponses, "Candidates listed");
	}

}
