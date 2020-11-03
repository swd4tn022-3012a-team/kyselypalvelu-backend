package com.example.kyselypalvelu;

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
			//Luodaan kysely, jolle annetaan parametreina otsikko ja kuvaus
			Questionnaire questionnaire = new Questionnaire("Henkilökysely", "Tämä on henkilötietokysely");
			
			//Tallennetaan kysely kantaan ja tulostetaan siitä tietoja
			log.info("FIRST QUESTIONNAIRE ID:" + questionnaires.save(questionnaire).getQuestionnaireId().toString());
			
			//Luodaan kaksi uutta kysymystä, jolle parametrina kysymyksen teksti  ja kysely, johon kysymys liittyy
			Question first = new Question("Mikä on nimesi?", questionnaire);
			Question second = new Question("Kuinka vanha olet?", questionnaire);
			
			//Tallennetaan kysymykset kantaan
			questions.save(first);
			questions.save(second);
		};
	}

}
