package com.example.kyselypalvelu.webcontroller;

import java.util.List;
import java.util.Optional;

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
import com.example.kyselypalvelu.domain.Questionnaire;

@Controller
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;

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
	@RequestMapping(value = "/answers/{questionId}", method = RequestMethod.GET)
	public @ResponseBody List<Answer> findQuestionAnswers(@PathVariable("questionId") Long questionId) {
		return answerRepository.findByQuestionQuestionId(questionId);
	}

}
