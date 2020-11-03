package com.example.kyselypalvelu.webcontroller;

import com.example.kyselypalvelu.domain.Questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuestionnaireController {
	
	// questionnaire lomakkeen luonti
	@RequestMapping(value = "/newquestionnaire", method = RequestMethod.GET)
	public String getNewQuestionnaireForm(Model model) {
		model.addAttribute("questionnaire", new Questionnaire()); 
		return "questionnaireform";
		}
		
	// questionnaire syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/savequestionnaire", method = RequestMethod.POST)
	public String saveQuestionnaire(@ModelAttribute Questionnaire questionnaire, Model model) {
		model.addAttribute("questionnaire", questionnaire);
		return "questionnaireresult";
		}
}
	