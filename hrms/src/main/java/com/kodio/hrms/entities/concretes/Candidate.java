package com.kodio.hrms.entities.concretes;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name="userId")  
public class Candidate extends User {

	@Column(name = "name")
	private String name;

	@Column(name = "surName")
	private String surName;

	@Column(name = "nationalIdentity", unique = true)
	private String nationalIdentity;

	@Column(name = "birthdate")
	private Date birthDate;

}
