package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.entities.concretes.Cv;

public interface CvService {

	DataResult<Cv> add(Cv cv);
	DataResult<Cv> getByCandidate(int id);
	
}
