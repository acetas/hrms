package com.kodio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobAdvertsiment;

@Repository
public interface JobAdversimentRepository extends JpaRepository<JobAdvertsiment, Integer> {

	List<JobAdvertsiment> findByIsEnabled(boolean active);
	List<JobAdvertsiment> findByIsEnabled_OrderByListingDateAsc(boolean active);
	List<JobAdvertsiment> findByIsEnabled_OrderByListingDateDesc(boolean active);
		
	// TODO: Hata var !!
	
//	@Query(value = "Select new com.kodio.hrms.entities.dtos.JobAdvertsimentWithCompanyDto"
//			+ " (j.id, c.name, j.jobPosition, j.vacancy, j.listingDate, j.applicationDeadline) "
//			+ "From JobAdvertisement j Inner Join Employer e Where j.employerId = e.id"
//			+ "Inner Join Company c Where e.companyId = c.id:id", nativeQuery=true)
	
	
//	@Query(value = "Select new com.kodio.hrms.entities.dtos.JobAdvertsimentWithCompanyDto"
//			+ " (j.id, c.name, j.job_position_id, j.vacancy, j.listing_date, j.application_deadline) "
//			+ "From job_adversiments j Inner Join employers e Where j.employer_id = e.user_id"
//			+ "Inner Join companies c Where e.company_id = 1", nativeQuery=true)
	
	
//	@Query("Select new com.kodio.hrms.entities.dtos.JobAdvertsimentWithCompanyDto"
//	+ "(j.id, j.jobPositionId, j.vacancy, j.listingDate, j.applicationDeadline) "
//	+ "From Company c Join c.employers e "
//	+ "Join e.jobAdvertsiments j "
//	+ "Where c.id = ?1")
//	List<JobAdvertsimentWithCompanyDto> findByCompanyId(Integer id);
	
}

// Çalışan SQL sorgu (v1)

// Select j.id from companies c
// Inner Join employers e on c.id = e.company_id
// Inner Join job_adversiments j on j.employer_id = e.user_id
// where c.id = 1


//Çalışan SQL sorgu (v2)

//select j.id, c.name, j.job_position_id, j.vacancy, j.listing_date, j.application_deadline from job_adversiments j 
//Inner Join employers e on j.employer_id = e.user_id
//Inner Join companies c on e.company_id = 1



// Çalışan SQL sorgu (v3)

// select j.id, c.name, p.name, j.vacancy, j.listing_date, j.application_deadline from job_adversiments j 
// Inner Join employers e on j.employer_id = e.user_id
// Inner Join companies c on e.company_id = 1
// Inner Join job_positions p on j.job_position_id = p.id