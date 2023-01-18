package com.kodio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobAdvertisement;

@Repository
public interface JobAdversimentRepository extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findByIsEnabled(boolean active);
	List<JobAdvertisement> findByIsEnabled_OrderByListingDateAsc(boolean active);
	List<JobAdvertisement> findByIsEnabled_OrderByListingDateDesc(boolean active);
	
}
