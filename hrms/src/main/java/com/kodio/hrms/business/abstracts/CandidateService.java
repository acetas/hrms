package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.CandidateRequest;
import com.kodio.hrms.core.results.DataResult;

public interface CandidateService {
	
	DataResult<CandidateRequest> add(CandidateRequest candidateRequest);

}
