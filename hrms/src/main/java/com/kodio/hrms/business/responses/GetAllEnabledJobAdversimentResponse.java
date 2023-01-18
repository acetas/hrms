package com.kodio.hrms.business.responses;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kodio.hrms.entities.concretes.City;
import com.kodio.hrms.entities.concretes.Employer;
import com.kodio.hrms.entities.concretes.JobPosition;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllEnabledJobAdversimentResponse {
	
	private boolean isTemporary;
	private int validity;
	private int vacancy;
	private String title;
	private String description;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="listingDate")
	private Date listingDate;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="applicationDeadline")
	private Date applicationDeadline;
	
	private String minSalary;
	private String maxSalary;

	private City city;
	private JobPosition jobPosition;
	private Employer employer;
	
}
