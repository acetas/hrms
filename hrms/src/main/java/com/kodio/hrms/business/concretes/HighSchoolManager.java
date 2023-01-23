package com.kodio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.HighSchoolService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.HighSchoolRepository;
import com.kodio.hrms.entities.concretes.HighSchool;

@Service
public class HighSchoolManager implements HighSchoolService {

	@Autowired
	private HighSchoolRepository highSchoolRepository;
	
	@Override
	public DataResult<HighSchool> add(HighSchool highSchool) {
		highSchoolRepository.save(highSchool);
		return new SuccessDataResult<HighSchool>(highSchool, "HighSchool added");
	}

	@Override
	public DataResult<HighSchool> update(int id, HighSchool highSchool) {
		HighSchool school = highSchoolRepository.findById(id).get();
		school.setName(highSchool.getName());
		return new SuccessDataResult<HighSchool>(school, "HighSchool edited");
	}

	@Override
	public Result delete(int id) {
		highSchoolRepository.deleteById(id);
		return new SuccessResult("HighSchool deleted");
	}

	@Override
	public DataResult<List<HighSchool>> getAll() {
		List<HighSchool> highSchools = highSchoolRepository.findAll();
		return new SuccessDataResult<List<HighSchool>>(highSchools, "HighSchools listed");
	}

}
