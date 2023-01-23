package com.kodio.hrms.business.responses;

import com.kodio.hrms.entities.concretes.Company;

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
public class EmployerResponse extends UserResponse {

	private String website;
	
	private String phone;
	
	private Company company;
	
}
