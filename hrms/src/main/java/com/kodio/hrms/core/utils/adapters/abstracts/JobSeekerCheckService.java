package com.kodio.hrms.core.utils.adapters.abstracts;

import com.kodio.hrms.business.requests.AddJobSeekerRequest;

public interface JobSeekerCheckService {

	boolean checkIfRealPerson(AddJobSeekerRequest addJobSeekerRequest);
	
}
