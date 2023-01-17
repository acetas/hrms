package com.kodio.hrms.business.abstracts;

import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.entities.concretes.Company;

public interface CompanyService {

	DataResult<Company> add(Company company);
	
}
