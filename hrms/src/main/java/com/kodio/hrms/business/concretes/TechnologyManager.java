package com.kodio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.TechnologyService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.TechnologyRepository;
import com.kodio.hrms.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService{

	@Autowired
	private TechnologyRepository technologyRepository;
	
	@Override
	public DataResult<Technology> add(Technology technology) {
		technologyRepository.save(technology);
		return new SuccessDataResult<Technology>(technology, "Technology added");
	}

	@Override
	public DataResult<Technology> update(int id, Technology technology) {
		Technology techno = technologyRepository.findById(id).get();
		techno.setName(technology.getName());
		return new SuccessDataResult<Technology>(techno, "Technology edited");
	}

	@Override
	public Result delete(int id) {
		technologyRepository.deleteById(id);
		return new SuccessResult("Technology deleted");
	}

	@Override
	public DataResult<List<Technology>> getAll() {
		List<Technology> technologies = technologyRepository.findAll();
		return new SuccessDataResult<List<Technology>>(technologies, "Technology listed");
	}

}
