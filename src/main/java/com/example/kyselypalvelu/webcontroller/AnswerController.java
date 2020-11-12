package com.example.kyselypalvelu.webcontroller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnswerController {
	
	
	@Autowired
	private AnswerRepository answerRepository;
	
	
	//Metodi tallentamaan vastaus Repositoryyn
	@RequestMapping(value= "/answers/{questionnaireId}", method = RequestMethod.POST)
	public String saveAnswer(@PathVariable("questionnaireId") Long questionnaireId, @RequestBody List<Answer> answers) {
		for (Answer answer : answers) {
			
		}
	}
	
	
	
	
}
	