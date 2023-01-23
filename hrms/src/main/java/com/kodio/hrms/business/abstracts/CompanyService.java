package com.kodio.hrms.business.abstracts;

import java.util.List;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.entities.concretes.Company;

public interface CompanyService {

	DataResult<Company> add(Company company);
	DataResult<Company> update(int id, Company company);
	DataResult<List<Company>> getAll();
	Result delete(int id);
	
}
