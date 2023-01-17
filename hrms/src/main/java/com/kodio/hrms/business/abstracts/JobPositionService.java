package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.JobPositionRequest;
import com.kodio.hrms.core.results.DataResult;

public interface JobPositionService {

	DataResult<JobPositionRequest> add(JobPositionRequest jobPositionRequest);
	
}
