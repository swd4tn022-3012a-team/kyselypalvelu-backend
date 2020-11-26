package com.example.kyselypalvelu.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name ="questionnaireId")
	private Questionnaire questionnaire;
	
	private String questionText;
	
	@OneToOne
	private Options options;
	
	@ManyToOne
	@JoinColumn(name="questionTypeId")
	private QuestionType type;

	public QuestionType getType() {
		return type;
	}

	public Question(String questionText, Questionnaire questionnaire, QuestionType type ) {
		super();
		this.questionnaire = questionnaire;
		this.questionText = questionText;
		this.type = type;
		this.options = null;
	}

	public Question(String questionText, Questionnaire questionnaire, QuestionType type, Options answerOptions) {
		super();
		this.questionnaire = questionnaire;
		this.questionText = questionText;
		this.options = answerOptions;
		this.type = type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options answerOptions) {
		this.options = answerOptions;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionnaire=" + questionnaire + ", questionText="
				+ questionText + "]";
	}

	public Question(String questionText, Questionnaire questionnaire) {
		super();	
		this.questionnaire = questionnaire;
		this.questionText = questionText;
	}
	
	public Question(String questionText) {
		super();
		this.questionText = questionText;
	}

	public Question() {
		super();
	}

}
