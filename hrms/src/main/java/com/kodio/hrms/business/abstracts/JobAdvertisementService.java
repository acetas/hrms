package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.business.requests.JobAdvertisementRequest;
import com.kodio.hrms.business.requests.UpdateJobAdvertisementRequest;
import com.kodio.hrms.business.responses.GetAllEnabledJobAdvertisementResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;

public interface JobAdvertisementService {

	DataResult<JobAdvertisementRequest> add(JobAdvertisementRequest jobAdvertisementRequest);
	Result update(int id, UpdateJobAdvertisementRequest updateJobAdvertisementRequest);
	Result delete(int id);
	DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledJobAdvertisement();
	
	DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledByListingDateAsc();
	DataResult<List<GetAllEnabledJobAdvertisementResponse>> getAllEnabledByListingDateDesc();	
	
	//DataResult<List<JobAdvertsimentWithCompanyDto>> getAllJobAdvForCompany(int id);	
	
	
}
