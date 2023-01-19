package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.Cv;

@Repository
public interface CvRepository extends JpaRepository<Cv, Integer> {
	
	Cv findByCandidateId(int id);

}
