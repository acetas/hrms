package com.kodio.hrms.entities.concretes;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cvs")
public class Cv {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "otherTechnology")
	private String[] otherTechnology;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
	private List<Education> educations;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
	private List<TechnologyKnowledge> technologyKnowledges;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
	private List<LanguageKnowledge> languageKnowledges;
	
	@OneToMany(mappedBy = "cv", cascade = CascadeType.ALL)
	private List<Experience> experiences;
	
	@OneToOne
	@JoinColumn(name = "candidateId")
	private Candidate candidate;
	
	@Column(name = "summary", columnDefinition = "TEXT")
	private String summary;
	
	
}
