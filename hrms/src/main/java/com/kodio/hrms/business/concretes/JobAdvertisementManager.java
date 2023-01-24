package com.kodio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.JobAdvertisementService;
import com.kodio.hrms.business.requests.JobAdvertisementRequest;
import com.kodio.hrms.business.requests.UpdateJobAdvertisementRequest;
import com.kodio.hrms.business.responses.GetAllEnabledJobAdvertisementResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.JobAdvertisementRepository;
import com.kodio.hrms.entities.concretes.JobAdvertisement;
import com.kodio.hrms.entities.dtos.JobAdvertisementWithCompanyDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	@Autowired
	private JobAdvertisementRepository jobAdversimentRepository;
	
	@Override
	public DataResult<JobAdvertisementRequest> add(JobAdvertisementRequest jobAdvertisementRequest) {
		
		JobAdvertisement jobAdvertisement = JobAdvertisement.builder()
		.isTemporary(jobAdvertisementRequest.isTemporary())
		.validity(jobAdvertisementRequest.getValidity())
		.vacancy(jobAdvertisementRequest.getVacancy())
		.title(jobAdvertisementRequest.getTitle())
		.description(jobAdvertisementRequest.getDescription())
		.applicationDeadline(jobAdvertisementRequest.getApplicationDeadline())
		.listingDate(jobAdvertisementRequest.getListingDate())
		.minSalary(jobAdvertisementRequest.getMinSalary())
		.maxSalary(jobAdvertisementRequest.getMaxSalary())
		.isEnabled(jobAdvertisementRequest.isEnabled())
		.cities(jobAdvertisementRequest.getCities())
		.jobPosition(jobAdvertisementRequest.getJobPosition())
		.employer(jobAdvertisementRequest.getEmployer())
		.build();
		
		jobAdversimentRepository.save(jobAdvertisement);
		
		return new SuccessDataResult<JobAdvertisementRequest>(jobAdvertisementRequest, "Job adversiment added");
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledJobAdvertisement() {
		
		List<JobAdvertisement> enabledJobAds = jobAdversimentRepository.findByIsEnabled(true);
		List<GetAllEnabledJobAdvertisementResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertisement enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdvertisementResponse jobAdvertisement = GetAllEnabledJobAdvertisementResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.cities(enabledJobAd.getCities())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdvertisementResponse>>(getAllEnabledJobAds, "Vacancies listed");
	
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledByListingDateAsc() {
		
		List<JobAdvertisement> enabledJobAds = jobAdversimentRepository.findByIsEnabled_OrderByListingDateAsc(true);
		List<GetAllEnabledJobAdvertisementResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertisement enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdvertisementResponse jobAdvertisement = GetAllEnabledJobAdvertisementResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.cities(enabledJobAd.getCities())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdvertisementResponse>>(getAllEnabledJobAds, "Vacancies listed, Sort Asc");
	
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledByListingDateDesc() {
		List<JobAdvertisement> enabledJobAds = jobAdversimentRepository.findByIsEnabled_OrderByListingDateDesc(true);
		List<GetAllEnabledJobAdvertisementResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertisement enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdvertisementResponse jobAdvertisement = GetAllEnabledJobAdvertisementResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.cities(enabledJobAd.getCities())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdvertisementResponse>>(getAllEnabledJobAds, "Vacancies listed, Sort Desc");
		
	}

	@Override
	public Result update(int id, UpdateJobAdvertisementRequest updateJobAdvertisementRequest) {
		
		JobAdvertisement jobAdvertisement = jobAdversimentRepository.findById(id).get();
		
		jobAdvertisement.setTemporary(updateJobAdvertisementRequest.isTemporary());
		jobAdvertisement.setRemote(updateJobAdvertisementRequest.isRemote());
		jobAdvertisement.setOnSite(updateJobAdvertisementRequest.isOnSite());
		jobAdvertisement.setFullTime(updateJobAdvertisementRequest.isFullTime());
		jobAdvertisement.setPartTime(updateJobAdvertisementRequest.isPartTime());
		
		if(updateJobAdvertisementRequest.getValidity() != 0) {
			jobAdvertisement.setValidity(updateJobAdvertisementRequest.getValidity());
		}
		
		if(updateJobAdvertisementRequest.getVacancy() != 0) {
			jobAdvertisement.setVacancy(updateJobAdvertisementRequest.getVacancy());
		}
		
		if(updateJobAdvertisementRequest.getTitle() != null) {
			jobAdvertisement.setTitle(updateJobAdvertisementRequest.getTitle());
		}
		
		if(updateJobAdvertisementRequest.getDescription() != null) {
			jobAdvertisement.setDescription(updateJobAdvertisementRequest.getDescription());
		}
		
		if(updateJobAdvertisementRequest.getApplicationDeadline() != null) {
			jobAdvertisement.setApplicationDeadline(updateJobAdvertisementRequest.getApplicationDeadline());
		}
		
		if(updateJobAdvertisementRequest.getMinSalary() != null) {
			jobAdvertisement.setMinSalary(updateJobAdvertisementRequest.getMinSalary());
		}
		
		if(updateJobAdvertisementRequest.getMaxSalary() != null) {
			jobAdvertisement.setMaxSalary(updateJobAdvertisementRequest.getMaxSalary());
		}
		
		if(updateJobAdvertisementRequest.getCities() != null) {
			jobAdvertisement.setCities(updateJobAdvertisementRequest.getCities());
		}
		
		if(updateJobAdvertisementRequest.getJobPosition() != null) {
			jobAdvertisement.setJobPosition(updateJobAdvertisementRequest.getJobPosition());
		}
		
		jobAdversimentRepository.save(jobAdvertisement);
		
		return new SuccessDataResult<JobAdvertisement>(jobAdvertisement, "Job Advertsiment edited");
	}

	@Override
	public Result delete(int id) {
		jobAdversimentRepository.deleteById(id);
		return new SuccessResult("Job Advertisement deleted");
	}

	@Override
	public DataResult<List<JobAdvertisementWithCompanyDto>> getAllJobAdvForCompany(int id) {
		
		return new SuccessDataResult<List<JobAdvertisementWithCompanyDto>>(jobAdversimentRepository.findByCompanyId(id), "Job Advertsiment listed for Company");
		
	}

}
