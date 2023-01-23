package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.entities.concretes.Technology;

public interface TechnologyService {

	DataResult<Technology> add(Technology technology);
	DataResult<Technology> update(int id, Technology technology);
	Result delete(int id);
	DataResult<List<Technology>> getAll();
	
}
