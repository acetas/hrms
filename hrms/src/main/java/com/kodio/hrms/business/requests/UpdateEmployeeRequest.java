package com.kodio.hrms.business.requests;

import com.kodio.hrms.entities.concretes.ByCompany;
import com.kodio.hrms.entities.concretes.UserRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UpdateEmployeeRequest extends UpdateUserRequest {

	private String department;
	
	private ByCompany byCompany;
	
	private UserRole userRole;
	
}
