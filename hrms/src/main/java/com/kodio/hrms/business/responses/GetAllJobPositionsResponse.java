package com.kodio.hrms.business.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllJobPositionsResponse {

	private int id;
	private String name;
	
}
