package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.business.requests.UpdateCandidateRequest;
import com.kodio.hrms.business.responses.CandidateResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

import jakarta.mail.MessagingException;

public interface CandidateService {
	
	DataResult<CandidateRequest> add(CandidateRequest candidateRequest) throws UnsupportedEncodingException, MessagingException;

	Result update(Long id, UpdateCandidateRequest updateCandidateRequest);
	
	Result delete(Long id);
	
	DataResult<List<CandidateResponse>> getAll();
}
