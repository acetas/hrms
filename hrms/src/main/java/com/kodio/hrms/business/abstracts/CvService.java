package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.business.requests.CvRequest;
import com.kodio.hrms.business.requests.UpdateCvRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.entities.concretes.Cv;

public interface CvService {

	DataResult<CvRequest> add(CvRequest cvRequest);
	DataResult<UpdateCvRequest> update(Long id, UpdateCvRequest updateCvRequest);
	Result delete(Long id);
	DataResult<Cv> getByCandidate(Long id);
	
}
