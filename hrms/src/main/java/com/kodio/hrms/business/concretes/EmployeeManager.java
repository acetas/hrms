package com.kodio.hrms.business.concretes;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.EmployeeService;
import com.kodio.hrms.business.requests.EmployeeRequest;
import com.kodio.hrms.business.requests.UpdateEmployeeRequest;
import com.kodio.hrms.business.requests.UserRequest;
import com.kodio.hrms.business.responses.EmployeeResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.ErrorResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.core.utils.Encryptor;
import com.kodio.hrms.core.utils.mailSender.BaseMailSender;
import com.kodio.hrms.dataAccess.abstracts.EmployeeRepository;
import com.kodio.hrms.entities.concretes.Employee;

import jakarta.mail.MessagingException;

@Service
public class EmployeeManager implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private BaseUserManager baseUserManager;
	
	@Autowired
    private BaseMailSender baseMailSender;
	
	private String verifyUrl = "http://localhost:8080/api/user";

	@Override
	public DataResult<EmployeeRequest> add(EmployeeRequest employeeRequest)
			throws MalformedURLException, UnsupportedEncodingException, MessagingException {
		
		DataResult<UserRequest> result = baseUserManager.add(employeeRequest);
		employeeRequest.setPassword(result.getData().getPassword());
		
		if(result.isSuccess() == false) {
			
			return new ErrorDataResult<EmployeeRequest>(employeeRequest, result.getMessage());
			
		} else if(employeeRequest.getDepartment().isEmpty()){
			
			return new ErrorDataResult<EmployeeRequest>(employeeRequest, "Department cannot be empty");
			
		} else if(employeeRequest.getByCompany() == null){
			
			return new ErrorDataResult<EmployeeRequest>(employeeRequest, "By Company cannot be empty");
			
		} else if(employeeRequest.getUserRole() == null){
			
			return new ErrorDataResult<EmployeeRequest>(employeeRequest, "Role cannot be empty");
			
		} else {
			
			Employee employee = Employee.builder()
					.email(employeeRequest.getEmail())
					.username(employeeRequest.getUsername())
					.password(employeeRequest.getPassword())
					.department(employeeRequest.getDepartment())
					.byCompany(employeeRequest.getByCompany())
					.userRole(employeeRequest.getUserRole())
					.build();

			employeeRepository.save(employee);
			
			baseMailSender.sendVerificationEmail(employee, verifyUrl);

			return new SuccessDataResult<EmployeeRequest>(employeeRequest, "Employee added");
		}
			
	}

	@Override
	public Result update(Long id, UpdateEmployeeRequest updateEmployeeRequest) {

		Employee employee = employeeRepository.findById(id).get();
		
		Result result = baseUserManager.update(id, updateEmployeeRequest);
		
		if(!result.isSuccess()) {
			return new ErrorResult(result.getMessage());
		}
		
		if(updateEmployeeRequest.getPassword() != null && !updateEmployeeRequest.getPassword().isEmpty()) {
			
			String md5NewPassword = Encryptor.encryptPass(updateEmployeeRequest.getPassword());
			employee.setPassword(md5NewPassword);
		}
		
		if(updateEmployeeRequest.getUsername() != null && !updateEmployeeRequest.getUsername().isEmpty()) {
			employee.setUsername(updateEmployeeRequest.getUsername());
		}
		
		if(updateEmployeeRequest.getEmail() != null && !updateEmployeeRequest.getEmail().isEmpty()) {
			
			employee.setEmail(updateEmployeeRequest.getEmail());
			
			try {
				baseMailSender.sendVerificationEmail(employee, verifyUrl);
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
		}
		
		if(updateEmployeeRequest.getDepartment() != null && !updateEmployeeRequest.getDepartment().isEmpty()) {
			employee.setDepartment(updateEmployeeRequest.getDepartment());
		}
		
		if(updateEmployeeRequest.getByCompany() != null) {
			employee.setByCompany(updateEmployeeRequest.getByCompany());
		}
		
		if(updateEmployeeRequest.getUserRole() != null) {
			employee.setUserRole(updateEmployeeRequest.getUserRole());
		}

				
		employeeRepository.save(employee);
		
		EmployeeResponse employeeResponse = EmployeeResponse.builder()
				.email(employee.getEmail())
				.username(employee.getUsername())
				.department(employee.getDepartment())
				.byCompany(employee.getByCompany())
				.userRole(employee.getUserRole())
				.build();
		
		return new SuccessDataResult<EmployeeResponse>(employeeResponse, "Employee is edited");
		
	}

	@Override
	public Result delete(Long id) {
		
		employeeRepository.deleteById(id);
		return new SuccessResult("Employee deleted");
	}

	@Override
	public DataResult<List<EmployeeResponse>> getAll() {

		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		
		for (Employee employee : employees) {
			
			EmployeeResponse employeeResponse = EmployeeResponse.builder()
					.email(employee.getEmail())
					.username(employee.getUsername())
					.department(employee.getDepartment())
					.byCompany(employee.getByCompany())
					.userRole(employee.getUserRole())
					.build();
			
			employeeResponses.add(employeeResponse);
		}
		
		return new SuccessDataResult<List<EmployeeResponse>>(employeeResponses, "Employees listed");
		
	}

}
