package com.kodio.hrms.entities.concretes;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy")
	@JsonFormat(pattern="yyyy")
	@Column(name = "schoolStartYear")
	private Date schoolStartYear;
	
	@DateTimeFormat(pattern = "yyyy")
	@JsonFormat(pattern="yyyy")
	@Column(name = "schoolEndYear")
	private Date schoolEndYear;
	
	@NotNull
	@Column(name = "isGraduated")
	private boolean isGraduated;
	
}
