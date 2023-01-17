package com.kodio.hrms.business.abstracts;

import java.net.MalformedURLException;

import com.kodio.hrms.business.requests.AddEmployersRequests;
import com.kodio.hrms.core.results.DataResult;

public interface EmployerService {

	DataResult<AddEmployersRequests> add(AddEmployersRequests addEmployersRequests) throws MalformedURLException;
	
}
