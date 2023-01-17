package com.kodio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodio.hrms.entities.concretes.JobAdvertisement;

@Repository
public interface JobAdversimentRepository extends JpaRepository<JobAdvertisement, Integer> {

}
