package com.kodio.hrms.business.responses;

import com.kodio.hrms.entities.concretes.ByCompany;
import com.kodio.hrms.entities.concretes.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeResponse extends UserResponse {

	private String department;
	
	private ByCompany byCompany;
	
	private UserRole userRole;
	
}
