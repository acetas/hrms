package com.kodio.hrms.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private Long id;
	
	private String email;
	
	private String username;
	
	private boolean mailEnabled;
	
    private boolean adminEnabled;
	
}
