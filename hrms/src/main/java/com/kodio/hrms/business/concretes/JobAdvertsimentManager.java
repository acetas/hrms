package com.kodio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.JobAdvertsimentService;
import com.kodio.hrms.business.requests.JobAdversimentRequest;
import com.kodio.hrms.business.responses.GetAllEnabledJobAdversimentResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.dataAccess.abstracts.JobAdversimentRepository;
import com.kodio.hrms.entities.concretes.JobAdvertsiment;

@Service
public class JobAdvertsimentManager implements JobAdvertsimentService {

	@Autowired
	private JobAdversimentRepository jobAdversimentRepository;
	
	@Override
	public DataResult<JobAdversimentRequest> add(JobAdversimentRequest jobAdversimentRequest) {
		
		JobAdvertsiment jobAdvertisement = JobAdvertsiment.builder()
		.isTemporary(jobAdversimentRequest.isTemporary())
		.validity(jobAdversimentRequest.getValidity())
		.vacancy(jobAdversimentRequest.getVacancy())
		.title(jobAdversimentRequest.getTitle())
		.description(jobAdversimentRequest.getDescription())
		.applicationDeadline(jobAdversimentRequest.getApplicationDeadline())
		.listingDate(jobAdversimentRequest.getListingDate())
		.minSalary(jobAdversimentRequest.getMinSalary())
		.maxSalary(jobAdversimentRequest.getMaxSalary())
		.isEnabled(jobAdversimentRequest.isEnabled())
		.city(jobAdversimentRequest.getCity())
		.jobPosition(jobAdversimentRequest.getJobPosition())
		.employer(jobAdversimentRequest.getEmployer())
		.build();
		
		jobAdversimentRepository.save(jobAdvertisement);
		
		return new SuccessDataResult<JobAdversimentRequest>(jobAdversimentRequest, "Job adversiment added");
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledJobAdversiment() {
		
		List<JobAdvertsiment> enabledJobAds = jobAdversimentRepository.findByIsEnabled(true);
		List<GetAllEnabledJobAdversimentResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertsiment enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdversimentResponse jobAdvertisement = GetAllEnabledJobAdversimentResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.city(enabledJobAd.getCity())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdversimentResponse>>(getAllEnabledJobAds, "Vacancies listed");
	
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledByListingDateAsc() {
		
		List<JobAdvertsiment> enabledJobAds = jobAdversimentRepository.findByIsEnabled_OrderByListingDateAsc(true);
		List<GetAllEnabledJobAdversimentResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertsiment enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdversimentResponse jobAdvertisement = GetAllEnabledJobAdversimentResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.city(enabledJobAd.getCity())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdversimentResponse>>(getAllEnabledJobAds, "Vacancies listed, Sort Asc");
	
	}

	@Override
	public DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledByListingDateDesc() {
		List<JobAdvertsiment> enabledJobAds = jobAdversimentRepository.findByIsEnabled_OrderByListingDateDesc(true);
		List<GetAllEnabledJobAdversimentResponse> getAllEnabledJobAds = new ArrayList<>();
		
		
		for (JobAdvertsiment enabledJobAd : enabledJobAds) {
			GetAllEnabledJobAdversimentResponse jobAdvertisement = GetAllEnabledJobAdversimentResponse.builder()
					.isTemporary(enabledJobAd.isTemporary())
					.validity(enabledJobAd.getValidity())
					.vacancy(enabledJobAd.getVacancy())
					.title(enabledJobAd.getTitle())
					.description(enabledJobAd.getDescription())
					.listingDate(enabledJobAd.getListingDate())
					.applicationDeadline(enabledJobAd.getApplicationDeadline())
					.minSalary(enabledJobAd.getMinSalary())
					.maxSalary(enabledJobAd.getMaxSalary())
					.city(enabledJobAd.getCity())
					.jobPosition(enabledJobAd.getJobPosition())
					.employer(enabledJobAd.getEmployer())
					.build();
			
			getAllEnabledJobAds.add(jobAdvertisement);
			
		}
		
		return new SuccessDataResult<List<GetAllEnabledJobAdversimentResponse>>(getAllEnabledJobAds, "Vacancies listed, Sort Desc");
		
	}

//	@Override
//	public DataResult<List<JobAdvertsimentWithCompanyDto>> getAllJobAdvForCompany(int id) {
//		
//		return new SuccessDataResult<List<JobAdvertsimentWithCompanyDto>>(jobAdversimentRepository.findByCompanyId(id), "Job Advertsiment listed for Company");
//		
//	}

}
