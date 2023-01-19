package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CvService;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.dataAccess.abstracts.CvRepository;
import com.kodio.hrms.entities.concretes.Cv;

@Service
public class CvManager implements CvService {

	@Autowired
	private CvRepository cvRepository;
	
	@Override
	public DataResult<Cv> add(Cv cv) {
		
		cvRepository.save(cv);
		
		return new SuccessDataResult<Cv>(cv, "Cv added");
		
	}

}
