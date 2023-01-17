package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.AddJobSeekerRequest;
import com.kodio.hrms.core.results.DataResult;

public interface JobSeekerService {
	
	DataResult<AddJobSeekerRequest> add(AddJobSeekerRequest addJobSeekerRequest);

}
