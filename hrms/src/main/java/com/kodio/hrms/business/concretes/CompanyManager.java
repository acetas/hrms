package com.kodio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CompanyService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
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

	@Override
	public DataResult<Company> update(int id, Company company) {
		
		Company companyOriginal = companyRepository.findById(id).get();
		companyOriginal.setName(company.getName());
		
		companyRepository.save(companyOriginal);
		
		return new SuccessDataResult<Company>(companyOriginal, "Company edited");
	}

	@Override
	public DataResult<List<Company>> getAll() {
		List<Company> companies = companyRepository.findAll();
		return new SuccessDataResult<List<Company>>(companies, "Companies listed");
	}

	@Override
	public Result delete(int id) {
		companyRepository.deleteById(id);
		return new SuccessResult("Company deleted");
	}

	
	
}
