package com.kodio.hrms.core.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataResult<T> extends Result {

	private T data;
	
	public DataResult(T data, boolean success){
		super(success);
		this.data = data;
	}
	
	public DataResult(T data, boolean success, String message){
		super(success, message);
		this.data = data;
	}
	
}
