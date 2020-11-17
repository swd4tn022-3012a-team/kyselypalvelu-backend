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
	public CommandLineRunner initialQuestionsAndQuestionnaires(QuestionRepository questionRepository, QuestionnaireRepository questionnaireRepository) {
		return (args) -> {
			log.info("insert a test question and questionnaire");
			//Luodaan kysely, jolle annetaan parametreina otsikko ja kuvaus
			Questionnaire questionnaire = new Questionnaire("HH-esimerkkikysely", "Anna palautetta opintojaksosta");
			
			//Tallennetaan kysely kantaan ja tulostetaan siitä tietoja
			log.info("FIRST QUESTIONNAIRE ID:" + questionnaireRepository.save(questionnaire).getQuestionnaireId().toString());
			
			//Luodaan HH esimerkkikysely
			ArrayList<Question> questions = new ArrayList<Question>();
			questions.add(new Question("Tukivatko kurssin opiskelutavat oppimistasi?", questionnaire));
			questions.add(new Question("Missä olisi mielestäsi parannettavaa?", questionnaire));
			questions.add(new Question("Tunsitko saavasi apua kun sitä tarvitsit?", questionnaire));
			questions.add(new Question("Mikä oli sinusta merkittävintä mitä opit kurssin aikana?", questionnaire));
			questions.add(new Question("Oliko kurssi sinusta merkittävä?", questionnaire));
			questions.add(new Question("Miten itse kehittäisit kurssia?", questionnaire));
			questions.add(new Question("Missä kurssi sinusta onnistui?", questionnaire));
			questions.add(new Question("Minkä arvosanan annat opetuksesta? (1-5)", questionnaire));
			questions.add(new Question("Minkä arvosanan antaisit kurssin oppimateriaaleille? (1-5)", questionnaire));
			questions.add(new Question("Minkä arvosanan annat kokonaisuudessaan kurssille? (1-5)", questionnaire));
			
			//Tallennetaan kysymykset kantaan
			questionRepository.saveAll(questions);
		};
	}

}
