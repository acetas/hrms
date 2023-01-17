package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

	Employer getByEmail(String email);
	
	Employer getByUsername(String username);
	
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
		
}
