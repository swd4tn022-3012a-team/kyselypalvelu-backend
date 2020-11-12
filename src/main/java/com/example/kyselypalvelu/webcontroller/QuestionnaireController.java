package com.example.kyselypalvelu.webcontroller;

import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;
import com.example.kyselypalvelu.domain.Question;
import com.example.kyselypalvelu.domain.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionnaireController {
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	// Kyselyn luonti
	@RequestMapping(value = "/newquestionnaire", method = RequestMethod.GET)
	public String getNewQuestionnaireForm(Model model) {
		//Luodaan uusi kysely-olio
		Questionnaire questionnaire = new Questionnaire();
		
		//Luodaan kysymyslista, lisätään siihen kysymys ja annetaan se kysely-oliolle
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(new Question());
		questionnaire.setQuestions(questions);
		
		//Annetaan kysely thymeleafille attribuutiksi
		model.addAttribute("questionnaire", questionnaire);
		
		//Palautetaan kyselyn luomislomake
		return "questionnaireform";
	}
	
	// Kysymyksen lisäys kyselylomakkeeseen
	@RequestMapping(value = "/savequestionnaire", params = {"addQuestion"})
	public String addQuestion(@ModelAttribute Questionnaire questionnaire) {
		//Haetaan kyselyn kysymykset
		List<Question> questions = questionnaire.getQuestions();
		
		//Lisätään niihin uusi kysymys
		questions.add(new Question());
		//Annetaan päivitetty lista kysely-olio attribuutille
		questionnaire.setQuestions(questions);
		
		//Palautetaan kyselyn luomislomake jossa päivitetty kysely-olio
		return "questionnaireform";
	}
		
	// Lomakkeelle syötettyjen kysymysten vastaanotto ja tallennus tietokantaan
	@RequestMapping(value = "/savequestionnaire", method = RequestMethod.POST)
	public String saveQuestionnaire(@ModelAttribute Questionnaire questionnaire) {
		//Tallennetaan kysely tietokantaan, jotta sille saadaan id
		Questionnaire saved = questionnaireRepository.save(questionnaire);
		List<Question> questions = questionnaire.getQuestions();
		//Käydään kysymykset läpi, ja asetetaan niille viite oikeaan kyselyyn
		for(Question question: questions) {
			question.setQuestionnaire(saved);
		}
		questionRepository.saveAll(questions); //Tallennetaan kaikki kysymykset
		return "redirect:questionnaires/"+saved.getQuestionnaireId();
	}
	
	//Palauttaa id:n perusteella kyselyn json-oliona, jolla kentät questionnaireId, questions, title ja description
	@CrossOrigin
	@RequestMapping(value="/questionnaires/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Questionnaire> findQuestionnaireRest(@PathVariable("id") Long questionnaireId) {
		return questionnaireRepository.findById(questionnaireId);
	}
	
}
	