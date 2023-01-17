package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.JobAdversimentService;
import com.kodio.hrms.business.requests.JobAdversimentRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.dataAccess.abstracts.JobAdversimentRepository;
import com.kodio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdversimentManager implements JobAdversimentService {

	@Autowired
	private JobAdversimentRepository jobAdversimentRepository;
	
	@Override
	public DataResult<JobAdversimentRequest> add(JobAdversimentRequest jobAdversimentRequest) {
		
		JobAdvertisement jobAdvertisement = JobAdvertisement.builder()
		.isTemporary(jobAdversimentRequest.isTemporary())
		.validity(jobAdversimentRequest.getValidity())
		.title(jobAdversimentRequest.getTitle())
		.description(jobAdversimentRequest.getDescription())
		.applicationDeadline(jobAdversimentRequest.getApplicationDeadline())
		.minSalary(jobAdversimentRequest.getMinSalary())
		.maxSalary(jobAdversimentRequest.getMaxSalary())
		.isEnabled(jobAdversimentRequest.isEnabled())
		.city(jobAdversimentRequest.getCity())
		.jobPosition(jobAdversimentRequest.getJobPosition())
		.build();
		
		jobAdversimentRepository.save(jobAdvertisement);
		
		return new SuccessDataResult<JobAdversimentRequest>(jobAdversimentRequest, "Job adversiment added");
	}

}
