package com.kodio.hrms.business.requests;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodio.hrms.entities.concretes.City;
import com.kodio.hrms.entities.concretes.JobPosition;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobAdversimentRequest {

	private boolean isTemporary;
	
	private int validity;
	
	@NotNull
	private int vacancy;
	
	@NotNull
	private String title;

	@NotNull
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date applicationDeadline;
	
	private String minSalary;
	
	private String maxSalary;
	
	private boolean isEnabled;
	
	@NotNull
	private City city;

	@NotNull
	private JobPosition jobPosition;
	
}
