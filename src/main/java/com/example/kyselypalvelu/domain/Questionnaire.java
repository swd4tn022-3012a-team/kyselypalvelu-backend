package com.example.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Questionnaire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionnaireId;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
	private List<Question> questions;
	
	public Long getQuestionnaireId() {
		return questionnaireId;
	}
	
	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public Questionnaire(List<Question> questions) {
		super();
		this.questions = questions;
	}
	
	public Questionnaire() {
		super();
	}
	
}
