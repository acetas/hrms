package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.JobSeekerRequest;
import com.kodio.hrms.core.results.DataResult;

public interface JobSeekerService {
	
	DataResult<JobSeekerRequest> add(JobSeekerRequest addJobSeekerRequest);

}
