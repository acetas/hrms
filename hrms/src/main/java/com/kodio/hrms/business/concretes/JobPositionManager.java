package com.kodio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodio.hrms.business.abstracts.JobPositionService;
import com.kodio.hrms.business.requests.JobPositionRequest;
import com.kodio.hrms.business.requests.UpdateJobPositionRequest;
import com.kodio.hrms.business.responses.GetAllJobPositionsResponse;
import com.kodio.hrms.core.results.DataResult;
import com.kodio.hrms.core.results.ErrorDataResult;
import com.kodio.hrms.core.results.Result;
import com.kodio.hrms.core.results.SuccessDataResult;
import com.kodio.hrms.core.results.SuccessResult;
import com.kodio.hrms.dataAccess.abstracts.JobPositionRepository;
import com.kodio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {
	
	@Autowired
	private JobPositionRepository jobPositionRepository;

	@Override
	public DataResult<JobPositionRequest> add(JobPositionRequest jobPositionRequest) {
		
		if(jobPositionRepository.existsByName(jobPositionRequest.getName())) {
			return new ErrorDataResult<JobPositionRequest>(jobPositionRequest, "Job Position Name cannot be empty");
		}else {
			
			JobPosition jobPosition = JobPosition.builder()
					.name(jobPositionRequest.getName())
					.build();
			
			jobPositionRepository.save(jobPosition);
			
			return new SuccessDataResult<JobPositionRequest>(jobPositionRequest, "Job Position Added");
			
		}
	
	}

	@Override
	public DataResult<List<GetAllJobPositionsResponse>> getAll() {
		
		List<GetAllJobPositionsResponse> getAllJobPositions = new ArrayList<>();
		
		List<JobPosition> jobPositions = jobPositionRepository.findAll();
		
		for (JobPosition jobPosition : jobPositions) {
			
			GetAllJobPositionsResponse jobPositionBuild = GetAllJobPositionsResponse.builder()
					.id(jobPosition.getId())
					.name(jobPosition.getName())
					.build();
			
			getAllJobPositions.add(jobPositionBuild);
			
		}
		
		return new SuccessDataResult<List<GetAllJobPositionsResponse>>(getAllJobPositions, "All Job positions listed");
	}

	@Override
	public Result update(int id, UpdateJobPositionRequest updateJobPositionRequest) {
		
		JobPosition jobPosition = jobPositionRepository.findById(id).get();
		
		if(updateJobPositionRequest.getName() == null || updateJobPositionRequest.getName().isEmpty()) {
			return new ErrorDataResult<UpdateJobPositionRequest>(updateJobPositionRequest, "Job Position cannot be empty");
		}
		
		jobPosition.setName(updateJobPositionRequest.getName());
		
		jobPositionRepository.save(jobPosition);
		
		return new SuccessDataResult<UpdateJobPositionRequest>(updateJobPositionRequest, "Job Position edited");
	}

	@Override
	public Result delete(int id) {
		jobPositionRepository.deleteById(id);
		return new SuccessResult("Job Position deleted");
	}

}
