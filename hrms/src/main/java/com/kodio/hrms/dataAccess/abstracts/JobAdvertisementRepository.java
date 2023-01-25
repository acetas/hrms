package com.kodio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
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
			
	@Query("Select new com.kodio.hrms.entities.dtos.JobAdvertisementWithCompanyDto"
	+ "(j.id, c.name, p.name, j.vacancy, j.listingDate, j.applicationDeadline) "
	+ "From Company c Inner Join c.employers e "
	+ "Inner Join e.jobAdvertisements j "
	+ "Inner Join j.jobPosition p "
	+ "Where c.id = :id")
	List<JobAdvertisementWithCompanyDto> findByCompanyId(@Param("id") int id);
	
}