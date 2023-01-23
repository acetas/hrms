package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.entities.concretes.HighSchool;

public interface HighSchoolService {

	DataResult<HighSchool> add(HighSchool highSchool);
	DataResult<HighSchool> update(int id, HighSchool highSchool);
	Result delete(int id);
	DataResult<List<HighSchool>> getAll();
	
}
