package com.kodio.hrms.business.requests;

import java.util.List;

import com.kodio.hrms.entities.concretes.Candidate;
import com.kodio.hrms.entities.concretes.Education;
import com.kodio.hrms.entities.concretes.Experience;
import com.kodio.hrms.entities.concretes.LanguageKnowledge;
import com.kodio.hrms.entities.concretes.TechnologyKnowledge;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CvRequest {
	
	private String avatar;
	
	private String[] otherTechnology;
	
	private List<Education> educations;
	
	private List<TechnologyKnowledge> technologyKnowledges;
	
	private List<LanguageKnowledge> languageKnowledges;
	
	private List<Experience> experiences;
	
	private Candidate candidate;
	
	private String summary;
	
}
