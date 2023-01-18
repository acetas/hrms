package com.kodio.hrms.entities.concretes;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobAdversiments")
public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="isTemporary")
	private boolean isTemporary;
	
	@Column(name="validity")
	private int validity;
	
	@NotNull
	@Column(name="vacancy")
	private int vacancy;
	
	@NotNull
	@Column(name="title")
	private String title;

	@NotNull
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="listingDate")
	private Date listingDate;
	
	@NotNull
	@Column(name="applicationDeadline")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date applicationDeadline;

	
	@Column(name="minSalary")
	private String minSalary;
	
	@Column(name="maxSalary")
	private String maxSalary;
	
	@Column(name="isEnabled")
	private boolean isEnabled;
	
	@OneToOne
	@NotNull
	private City city;

	@ManyToOne
	@JoinColumn(name="jobPositionId")
	@NotNull
	private JobPosition jobPosition;
	
	@ManyToOne
	@JoinColumn(name="employerId")
	@NotNull
	private Employer employer;
	
}
