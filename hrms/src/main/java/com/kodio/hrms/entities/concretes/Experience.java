package com.kodio.hrms.entities.concretes;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "experiences")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name  = "name")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "jobPositionId")
	private JobPosition jobPosition;
	
	@DateTimeFormat(pattern = "yyyy")
	@JsonFormat(pattern="yyyy")
	@Column(name = "startYear")
	private Date startYear;
	
	@DateTimeFormat(pattern = "yyyy")
	@JsonFormat(pattern="yyyy")
	@Column(name = "endYear")
	private Date endYear;
	
	@Column(name = "isActive")
	private boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cvId")
	private Cv cv;
	
}
