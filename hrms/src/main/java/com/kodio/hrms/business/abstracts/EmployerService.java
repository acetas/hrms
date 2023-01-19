package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.kodio.hrms.business.requests.EmployerRequest;
import com.kodio.hrms.core.results.DataResult;

import jakarta.mail.MessagingException;

public interface EmployerService {

	DataResult<EmployerRequest> add(EmployerRequest addEmployersRequests) throws MalformedURLException, UnsupportedEncodingException, MessagingException;
	
}
