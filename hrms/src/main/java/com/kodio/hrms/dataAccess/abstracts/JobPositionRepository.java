package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobPosition;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {
	
	boolean existsByName(String name);
	
}
