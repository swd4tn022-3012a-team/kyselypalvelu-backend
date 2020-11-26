package com.example.kyselypalvelu.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Answer {
	// attribuutit
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long answerId;
	public String text;
	
	// relaatiot
	@ManyToOne
	// @JsonIgnoreProperties - vältetään tällä loputtomat loopit kahdensuuntaisten relaatioiden JSON serialisaatioissa
	@JsonIgnoreProperties({"options", "type"})
	@JoinColumn(name = "questionId")
	private Question question;
	
	// konstruktori ilman attribuutteja
	public Answer() {}
	
	// konstruktorit
	public Answer(String text, Question question) {
		super();
		this.text = text;
		this.question = question;
	}
	
	// getterit ja setterit
	public Long getanswerId() {
		return answerId;
	}
	public void setanswerId(Long answerId) {
		this.answerId = answerId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	// toString
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", text=" + text + ", question =" + this.getQuestion() + "]";
	}
}
