package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	Candidate getByEmail(String email);
	
	Candidate getByUsername(String username);
	
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
	
	boolean existsByNationalIdentity(String nationalId);

}
