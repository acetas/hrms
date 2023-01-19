package com.kodio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "languageKnowledges")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
public class LanguageKnowledge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Size(min = 1, max = 5)
	@Column(name = "level")
	private int level;
	
	@OneToOne
	@JoinColumn(name = "languageId")
	private Language language;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cvId")
	private Cv cv;
}
