package com.kodio.hrms.core.utils.adapters.abstracts;

import com.kodio.hrms.business.requests.JobSeekerRequest;

public interface JobSeekerCheckService {

	boolean checkIfRealPerson(JobSeekerRequest addJobSeekerRequest);
	
}
