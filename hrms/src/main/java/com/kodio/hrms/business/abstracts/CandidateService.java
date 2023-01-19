package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;

import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.core.results.DataResult;

import jakarta.mail.MessagingException;

public interface CandidateService {
	
	DataResult<CandidateRequest> add(CandidateRequest candidateRequest) throws UnsupportedEncodingException, MessagingException;

}
