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
	private int id;
	
	@Column(name="isTemporary")
	private boolean isTemporary;
	
	@Column(name="validity")
	private int validity;
	
	@Column(name="vacancy")
	private int vacancy;
	
	@Column(name="title")
	private String title;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="listingDate")
	private Date listingDate;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="applicationDeadline")
	private Date applicationDeadline;

	
	@Column(name="minSalary")
	private String minSalary;
	
	@Column(name="maxSalary")
	private String maxSalary;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@OneToOne
	private City city;

	@ManyToOne
	@JoinColumn(name="jobPositionId")
	private JobPosition jobPosition;
	
}
