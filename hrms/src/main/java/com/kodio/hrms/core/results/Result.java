package com.kodio.hrms.core.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

	private boolean success;
	private String message;
	
	public Result(boolean success) {
		this.success = success;
	}
	
}
