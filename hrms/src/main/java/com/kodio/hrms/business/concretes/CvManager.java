package com.kodio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.CvService;
import com.kodio.hrms.business.requests.CvRequest;
import com.kodio.hrms.business.requests.UpdateCvRequest;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.CvRepository;
import com.kodio.hrms.entities.concretes.Cv;

@Service
public class CvManager implements CvService {

	@Autowired
	private CvRepository cvRepository;
	
	@Override
	public DataResult<CvRequest> add(CvRequest cvRequest) {
		
		Cv cv = new Cv();
		cv.setAvatar(cvRequest.getAvatar());
		cv.setCandidate(cvRequest.getCandidate());
		cv.setSummary(cvRequest.getSummary());
		cv.setEducations(cvRequest.getEducations());
		cv.setExperiences(cvRequest.getExperiences());
		cv.setLanguageKnowledges(cvRequest.getLanguageKnowledges());
		cv.setTechnologyKnowledges(cvRequest.getTechnologyKnowledges());
		cv.setOtherTechnology(cvRequest.getOtherTechnology());
		
		cvRepository.save(cv);
		
		return new SuccessDataResult<CvRequest>(cvRequest, "Cv added");
		
	}

	@Override
	public DataResult<Cv> getByCandidate(Long id) {
		return new SuccessDataResult<Cv>(cvRepository.findByCandidateId(id), "Cv listed");
	}

	@Override
	public DataResult<UpdateCvRequest> update(Long id, UpdateCvRequest updateCvRequest) {
		Cv cv = cvRepository.findById(id).get();
		
		if(updateCvRequest.getAvatar() != null) {
			cv.setAvatar(updateCvRequest.getAvatar());
		}
		
		if(updateCvRequest.getSummary() != null) {
			cv.setSummary(updateCvRequest.getSummary());
		}
		
		if(updateCvRequest.getEducations() != null) {
			cv.setEducations(updateCvRequest.getEducations());
		}
		
		if(updateCvRequest.getExperiences() != null) {
			cv.setExperiences(updateCvRequest.getExperiences());
		}
		
		if(updateCvRequest.getLanguageKnowledges() != null) {
			cv.setLanguageKnowledges(updateCvRequest.getLanguageKnowledges());
		}
		
		if(updateCvRequest.getTechnologyKnowledges() != null) {
			cv.setTechnologyKnowledges(updateCvRequest.getTechnologyKnowledges());
		}
		
		if(updateCvRequest.getOtherTechnology() != null) {
			cv.setOtherTechnology(updateCvRequest.getOtherTechnology());
		}
		
		cvRepository.save(cv);
		
		return new SuccessDataResult<UpdateCvRequest>(updateCvRequest, "Cv edited");
	}

	@Override
	public Result delete(Long id) {
		cvRepository.deleteById(id);
		return new SuccessResult("Cv deleted");
	}

}
