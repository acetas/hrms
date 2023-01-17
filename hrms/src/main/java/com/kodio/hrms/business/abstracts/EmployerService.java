package com.kodio.hrms.business.abstracts;

import java.net.MalformedURLException;

import com.kodio.hrms.business.requests.EmployersRequest;
import com.kodio.hrms.core.results.DataResult;

public interface EmployerService {

	DataResult<EmployersRequest> add(EmployersRequest addEmployersRequests) throws MalformedURLException;
	
}
