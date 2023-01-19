package com.kodio.hrms.core.utils.adapters.abstracts;

import com.kodio.hrms.business.requests.CandidateRequest;

public interface CandidateCheckService {

	boolean checkIfRealPerson(CandidateRequest candidateRequest);
	
}
