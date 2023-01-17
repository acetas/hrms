package com.kodio.hrms.entities.concretes;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)  
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="username", unique = true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Builder.Default
	@Column(name = "verification_code", length = 64)
    private String verificationCode = RandomStringUtils.randomAlphabetic(64);
    
	@Column(name="mailEnabled")
	private boolean mailEnabled;
	
	@Column(name="adminEnabled")
    private boolean adminEnabled;
		
}
