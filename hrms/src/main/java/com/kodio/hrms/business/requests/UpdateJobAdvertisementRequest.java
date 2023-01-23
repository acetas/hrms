package com.kodio.hrms.business.requests;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodio.hrms.entities.concretes.City;
import com.kodio.hrms.entities.concretes.JobPosition;

import lombok.Data;

@Data
public class UpdateJobAdvertisementRequest {

	private boolean isTemporary;
	
	private int validity;
	
	private int vacancy;
	
	private String title;

	private String description;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date applicationDeadline;
	
	private String minSalary;
	
	private String maxSalary;
		
	private boolean isRemote;
	
	private boolean isOnSite;
	
	private boolean isFullTime;
	
	private boolean isPartTime;
	
	private List<City> cities;

	private JobPosition jobPosition;
	
}
