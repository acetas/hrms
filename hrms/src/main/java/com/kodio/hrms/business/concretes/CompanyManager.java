package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CompanyService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.dataAccess.abstracts.CompanyRepository;
import com.kodio.hrms.entities.concretes.Company;

@Service
public class CompanyManager implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public DataResult<Company> add(Company company) {
		
		companyRepository.save(company);
		return new SuccessDataResult<Company>(company, "Company added");
	}

	
	
}
