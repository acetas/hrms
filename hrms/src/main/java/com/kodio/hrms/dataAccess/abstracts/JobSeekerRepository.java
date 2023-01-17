package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	JobSeeker getByEmail(String email);
	
	JobSeeker getByUsername(String username);
	
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
	boolean existsByNationalIdentity(String nationalId);

}
