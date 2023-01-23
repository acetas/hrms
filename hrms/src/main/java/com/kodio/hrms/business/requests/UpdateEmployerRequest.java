package com.kodio.hrms.business.requests;

import com.kodio.hrms.entities.concretes.Company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployerRequest extends UpdateUserRequest {

	private String website;
	
	private String phone;
	
	private Company company;
	
}
