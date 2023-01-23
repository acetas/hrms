package com.kodio.hrms.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "employees")
@PrimaryKeyJoinColumn(name="userId")  
public class Employee extends User {

	@Column(name = "department")
	private String department;
	
	@ManyToOne
	@JoinColumn(name = "byCompanyId")
	private ByCompany byCompany;
	
	@ManyToOne
	@JoinColumn(name = "userRoleId")
	private UserRole userRole;

}
