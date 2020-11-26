package com.example.kyselypalvelu.webcontroller;

import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;

import com.example.kyselypalvelu.domain.Question;

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
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuestionnaireController {
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listQuestionnaires(Model model) {	
		//Haetaan kyselyt tietokannasta
		List<Questionnaire> questionnaires = (List<Questionnaire>) questionnaireRepository.findAll();	
		model.addAttribute("questionnaires", questionnaires);	
		return "home";
	}
	
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
	
	//Yhden kyselyn thymeleaf näkymä
	@RequestMapping(value = "/questionnaire/{questionnaireId}", method = RequestMethod.GET)
	public String getQuestionnaire(@PathVariable("questionnaireId") Long questionnaireId, Model model) {
		
		//Haetaan kysely tietokannasta
		Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId).get();
	
		model.addAttribute("questionnaire", questionnaire);
		return "questionnaire";
	}
	
	//Kyselyn tallennus
	@RequestMapping(value = "/newquestionnaire", method = RequestMethod.POST)
	public String saveQuestionnaire(@ModelAttribute Questionnaire questionnaire, Model model) {
		
		questionnaireRepository.save(questionnaire);

		model.addAttribute("questionnaire", questionnaire);
		return "questionnaire";
	}
	
	
	// RESTful hakee kaikki kyselyt JSON-olioina
	@GetMapping("/questionnaires")
	public @ResponseBody List<Questionnaire> questionnairesListRest() {
		return (List<Questionnaire>) questionnaireRepository.findAll();
	}
	
	//RESTful Palauttaa id:n perusteella kyselyn JSON-oliona
	@CrossOrigin
	@RequestMapping(value="/questionnaires/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Questionnaire> findQuestionnaireRest(@PathVariable("id") Long questionnaireId) {
		return questionnaireRepository.findById(questionnaireId);
	}

	//REST-kotisivu
	@GetMapping("/resthome")
	public String restHomePage() {
		return "resthome";
	}

}
	