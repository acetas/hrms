package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.business.requests.JobPositionRequest;
import com.kodio.hrms.business.requests.UpdateJobPositionRequest;
import com.kodio.hrms.business.responses.GetAllJobPositionsResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

public interface JobPositionService {

	DataResult<JobPositionRequest> add(JobPositionRequest jobPositionRequest);
	
	Result update(int id, UpdateJobPositionRequest updateJobPositionRequest);
	
	Result delete(int id);
	
	DataResult<List<GetAllJobPositionsResponse>> getAll();
	
}
