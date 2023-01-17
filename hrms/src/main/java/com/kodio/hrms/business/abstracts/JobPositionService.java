package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.business.requests.JobPositionRequest;
import com.kodio.hrms.business.responses.GetAllJobPositionsResponse;
import com.kodio.hrms.core.results.DataResult;

public interface JobPositionService {

	DataResult<JobPositionRequest> add(JobPositionRequest jobPositionRequest);
	
	DataResult<List<GetAllJobPositionsResponse>> getAll();
	
}
