package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.business.requests.JobAdversimentRequest;
import com.kodio.hrms.business.responses.GetAllEnabledJobAdversimentResponse;
import com.kodio.hrms.core.results.DataResult;

public interface JobAdversimentService {

	DataResult<JobAdversimentRequest> add(JobAdversimentRequest jobAdversimentRequest);
	DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledJobAdversiment();
	
	DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledByListingDateAsc();
	DataResult<List<GetAllEnabledJobAdversimentResponse>> getAllEnabledByListingDateDesc();	
	
}
