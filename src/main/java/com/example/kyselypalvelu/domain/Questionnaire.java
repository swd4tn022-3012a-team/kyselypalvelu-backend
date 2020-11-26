package com.example.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Questionnaire {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionnaireId;

	private String title;
	private String description;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Questionnaire (String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	public Questionnaire() {
		super();
	}
	@Override
	public String toString() {
		return "Questionnaire [questionnaireId=" + questionnaireId + ", questions=" + questions + ", title=" + title
				+ ", description=" + description + "]";
	}
}
