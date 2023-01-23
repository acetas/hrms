package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import com.kodio.hrms.business.requests.EmployerRequest;
import com.kodio.hrms.business.requests.UpdateEmployerRequest;
import com.kodio.hrms.business.responses.EmployerResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

import jakarta.mail.MessagingException;

public interface EmployerService {

	DataResult<EmployerRequest> add(EmployerRequest addEmployersRequests) throws MalformedURLException, UnsupportedEncodingException, MessagingException;
	
	Result update(Long id, UpdateEmployerRequest updateEmployerRequest);
	
	Result delete(Long id);
	
	DataResult<List<EmployerResponse>> getAll();
	
}
