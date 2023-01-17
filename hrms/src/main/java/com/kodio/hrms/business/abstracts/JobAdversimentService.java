package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.JobAdversimentRequest;
import com.kodio.hrms.core.results.DataResult;

public interface JobAdversimentService {

	DataResult<JobAdversimentRequest> add(JobAdversimentRequest jobAdversimentRequest);
	
}
