package com.kodio.hrms.business.abstracts;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import com.kodio.hrms.business.requests.EmployeeRequest;
import com.kodio.hrms.business.requests.UpdateEmployeeRequest;
import com.kodio.hrms.business.responses.EmployeeResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

import jakarta.mail.MessagingException;

public interface EmployeeService {

	DataResult<EmployeeRequest> add(EmployeeRequest employeeRequest) throws MalformedURLException, UnsupportedEncodingException, MessagingException;
	
	Result update(Long id, UpdateEmployeeRequest updateEmployeeRequest);
	
	Result delete(Long id);
	
	DataResult<List<EmployeeResponse>> getAll();
	
}
