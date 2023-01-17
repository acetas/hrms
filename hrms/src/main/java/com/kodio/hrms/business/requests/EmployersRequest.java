package com.kodio.hrms.business.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EmployersRequest extends UserRequest {

	@NotNull
	@NotBlank
	private String companyName;
	
	@NotNull
	@NotBlank
	private String website;
	
	@NotNull
	@NotBlank
	private String phone;
	
}
