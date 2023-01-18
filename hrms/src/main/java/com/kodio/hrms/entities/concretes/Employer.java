package com.kodio.hrms.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "userId")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertsiments","company"})
public class Employer extends User {

	@Column(name = "webSite")
	private String website;
	
	@Column(name = "phone")
	private String phone;

	@OneToMany(mappedBy = "employer")
	private List<JobAdvertsiment> jobAdvertsiments;
	
	@ManyToOne
	@JoinColumn(name = "companyId")
	private Company company;
	
}
