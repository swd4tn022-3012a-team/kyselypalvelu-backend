package com.example.kyselypalvelu;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.kyselypalvelu.domain.Question;
import com.example.kyselypalvelu.domain.QuestionRepository;
import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;

@SpringBootApplication
public class KyselypalveluApplication {
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initialQuestionsAndQuestionnaires(QuestionRepository questions, QuestionnaireRepository questionnaires) {
		return (args) -> {
			log.info("insert a test question and questionnaire");
			
			Question question = new Question("Toimiiko tämä?");
			ArrayList<Question> questionList = new ArrayList<Question>();
			
			Questionnaire questionnaire = new Questionnaire(questionList);
			
			questions.save(question);
			questionnaires.save(questionnaire);
				    
		};
	}

}
