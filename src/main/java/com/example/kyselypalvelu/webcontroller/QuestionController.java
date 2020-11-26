package com.example.kyselypalvelu.webcontroller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kyselypalvelu.domain.Options;
import com.example.kyselypalvelu.domain.OptionsRepository;
import com.example.kyselypalvelu.domain.Question;
import com.example.kyselypalvelu.domain.QuestionRepository;
import com.example.kyselypalvelu.domain.QuestionTypeRepository;
import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;

@Controller
public class QuestionController {
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private OptionsRepository optionsRepository;
	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	//Kysymyksen lisäyslomakkeen haku
	@RequestMapping(value = "/addquestion/{questionnaireId}", method = RequestMethod.GET)
	public String addQuestion(@PathVariable("questionnaireId") Long questionnaireId, Model model) {

		Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).get();

		model.addAttribute("questionnaire", questionnaire);

		Question question = new Question();

		question.setOptions(new Options(Arrays.asList("Vaihtoehto 1")));

		model.addAttribute("question", question);

		model.addAttribute("questionTypes", questionTypeRepository.findAll());

		return "addquestion";
	}
	
	//Kysymyksen lisäys kyselyyn
	@RequestMapping(value = "/addquestion/{questionnaireId}", method = RequestMethod.POST)
	public String saveAddedQuestion(@PathVariable("questionnaireId") Long questionnaireId,
			@ModelAttribute Questionnaire questionnaire, @ModelAttribute Question question, Model model) {

		if (question.getType().getName().equals("text")) {
			question.setOptions(null);
			question.setQuestionnaire(questionnaire);

			questionRepository.save(question);

			model.addAttribute("questionnaire", questionnaireRepository.findById(questionnaireId).get());
			return "questionnaire";

		}

		optionsRepository.save(question.getOptions());

		question.setQuestionnaire(questionnaire);
		questionRepository.save(question);

		model.addAttribute("questionnaire", questionnaireRepository.findById(questionnaireId).get());
		return "questionnaire";
	}

	// Vaihtoehdon lisäys kysymykseen
	@RequestMapping(value = "/addquestion/{questionnaireId}", params = { "addOption" })
	public String addQuestionWithOption(@PathVariable("questionnaireId") Long questionnaireId,
			@ModelAttribute("question") Question question, Model model) {
		Options newOptions = question.getOptions();

		List<String> optionValues = newOptions.getValues();
		optionValues.add("Vaihtoehto " + (question.getOptions().getValues().size() + 1));

		newOptions.setValues(optionValues);
		question.setOptions(newOptions);

		model.addAttribute("question", question);

		model.addAttribute("questionnaire", questionnaireRepository.findById(questionnaireId).get());

		model.addAttribute("questionTypes", questionTypeRepository.findAll());

		return "addquestion";
	}

}
