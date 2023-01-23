package com.kodio.hrms.business.responses;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CandidateResponse extends UserResponse {

	private String name;
	private String surName;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date birthDate;
	
}
