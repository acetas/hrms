package com.kodio.hrms.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementWithCompanyDto {

	private int id;
	private String company;
	private String jobPosition;
	private int vacancy;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date listingDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date applicationDeadline;
}
