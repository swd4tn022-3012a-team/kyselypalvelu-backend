package com.example.kyselypalvelu.webcontroller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.kyselypalvelu.domain.Answer;
import com.example.kyselypalvelu.domain.AnswerRepository;
import com.example.kyselypalvelu.domain.AnswerStatistics;
import com.example.kyselypalvelu.domain.Question;
import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;

@Controller
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	// Metodi tallentamaan vastaus Repositoryyn
	@CrossOrigin
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/answers/{questionnaireId}", method = RequestMethod.POST)
	public void saveAnswers(@PathVariable("questionnaireId") Long questionnaireId, @RequestBody List<Answer> answers) {
		System.out.println("answer posted, questionnaireId: " + questionnaireId);
		answerRepository.saveAll(answers);

	}

	// Metodi näyttämään yhden kysymyksen vastaukset
	@CrossOrigin
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/answers/{questionnaireId}/{questionId}", method = RequestMethod.GET)
	public @ResponseBody List<Answer> findQuestionAnswers(@PathVariable("questionId") Long questionId) {
		return answerRepository.findByQuestionQuestionId(questionId);
	}

	// Metodi, joka hakee fronttiin yhden kyselyn kaikki vastaukset
	@CrossOrigin
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/answers/{questionnaireId}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Answer> findQuestionnaireAnswers(
			@PathVariable("questionnaireId") Long questionnaireId) {
		Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).get();
		List<Question> questions = questionnaire.getQuestions();
		ArrayList<Answer> answers = new ArrayList<>();
		for (Question question : questions) {
			answers.addAll(answerRepository.findByQuestionQuestionId(question.getQuestionId()));
		}
		System.out.println("VASTAUKSIA");
		return answers;
	}

	//Metodi joka hakee kysymyksen id:llä statistiikoita
	@RequestMapping(value = "/answerstatistics/{questionId}", method = RequestMethod.GET)
	public @ResponseBody List<AnswerStatistics> getAnswerStatistics(@PathVariable("questionId") Long questionId) {
		return answerRepository.findAnswerStatistics(questionId);
	}

}
