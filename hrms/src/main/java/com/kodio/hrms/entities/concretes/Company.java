package com.kodio.hrms.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employers"})
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "image")
	@NotNull
	private String image;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	@NotNull
	private String description;
	
	@OneToMany(mappedBy = "company")
	List<Employer> employers;
	
}
