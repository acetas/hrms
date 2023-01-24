package com.kodio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobAdvertisement;
import com.kodio.hrms.entities.dtos.JobAdvertisementWithCompanyDto;

@Repository
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Integer> {

	List<JobAdvertisement> findByIsEnabled(boolean active);
	List<JobAdvertisement> findByIsEnabled_OrderByListingDateAsc(boolean active);
	List<JobAdvertisement> findByIsEnabled_OrderByListingDateDesc(boolean active);
		
	// TODO: Hata var !!
	
	@Modifying
	@Query("Select new com.kodio.hrms.entities.dtos.JobAdvertisementWithCompanyDto"
	+ "(j.id, c.name, j.jobPosition, j.vacancy, j.listingDate, j.applicationDeadline) "
	+ "From Company c Inner Join c.employers e "
	+ "Inner Join JobAdvertisement j "
	+ "Where c.id = :id")
	List<JobAdvertisementWithCompanyDto> findByCompanyId(@Param("id") int id);
	
}



// Çalışan SQL sorgu (v3)

// select j.id, c.name, p.name, j.vacancy, j.listing_date, j.application_deadline from job_adversiments j 
// Inner Join employers e on j.employer_id = e.user_id
// Inner Join companies c on e.company_id = 1
// Inner Join job_positions p on j.job_position_id = p.id