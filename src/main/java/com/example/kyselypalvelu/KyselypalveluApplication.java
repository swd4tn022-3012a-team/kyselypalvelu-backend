package com.example.kyselypalvelu;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.kyselypalvelu.domain.Options;
import com.example.kyselypalvelu.domain.OptionsRepository;
import com.example.kyselypalvelu.domain.Question;
import com.example.kyselypalvelu.domain.QuestionRepository;
import com.example.kyselypalvelu.domain.QuestionType;
import com.example.kyselypalvelu.domain.QuestionTypeRepository;
import com.example.kyselypalvelu.domain.Questionnaire;
import com.example.kyselypalvelu.domain.QuestionnaireRepository;

@SpringBootApplication
public class KyselypalveluApplication {
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initialQuestionsAndQuestionnaires(QuestionRepository questions, QuestionnaireRepository questionnaires, OptionsRepository optionsRepo, QuestionTypeRepository questionTypes) {
		return (args) -> {
			log.info("insert a test question and questionnaire");
			//Luodaan kyselyt, joille annetaan parametreina otsikko ja kuvaus
			Questionnaire questionnaire = new Questionnaire("HH-esimerkkikysely", "Anna palautetta opintojaksosta");
			Questionnaire questionnaire2 = new Questionnaire("Kyselylomakkeen arviointi", "Anna palautetta kyselylomakkeesta");
			
			//Luodaan kysymystyypit
			QuestionType text = questionTypes.save(new QuestionType("text"));
			QuestionType radio = questionTypes.save(new QuestionType("radio"));
			QuestionType checkbox = questionTypes.save(new QuestionType("checkbox"));
			
			//Tallennetaan kyselyt kantaan ja tulostetaan niistä tietoja
			log.info("FIRST QUESTIONNAIRE ID:" + questionnaires.save(questionnaire).getQuestionnaireId().toString());
			log.info("SECOND QUESTIONNAIRE ID:" + questionnaires.save(questionnaire2).getQuestionnaireId().toString());
			
			//Luodaan pari esimerkkivastausvaihtoehtoa
			Options options = optionsRepo.save(new Options(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"))));
			Options options1 = optionsRepo.save(new Options(new ArrayList<>(Arrays.asList("Kyllä", "Neutraali", "Ei"))));
			
			//Luodaan HH esimerkkikysely
			ArrayList<Question> questionList = new ArrayList<Question>();
			questionList.add(new Question());
			questionList.add(new Question("Missä olisi mielestäsi parannettavaa?", questionnaire, text));
			questionList.add(new Question("Tunsitko saavasi apua kun sitä tarvitsit?", questionnaire, text));
			questionList.add(new Question("Mikä oli sinusta merkittävintä mitä opit kurssin aikana?", questionnaire, text));
			questionList.add(new Question("Oliko kurssi sinusta merkittävä?", questionnaire, checkbox, options1));
			questionList.add(new Question("Miten itse kehittäisit kurssia?", questionnaire, text));
			questionList.add(new Question("Missä kurssi sinusta onnistui?", questionnaire, text));
			questionList.add(new Question("Minkä arvosanan annat opetuksesta? (1-5)", questionnaire, radio, options));
			questionList.add(new Question("Minkä arvosanan antaisit kurssin oppimateriaaleille? (1-5)", questionnaire, text));
			questionList.add(new Question("Minkä arvosanan annat kokonaisuudessaan kurssille? (1-5)", questionnaire, text));
			
			//Luodaan Kyselypalvelun arviointi-kysely
			ArrayList<Question> questionList2 = new ArrayList<Question>();
			questionList2.add(new Question());
			questionList2.add(new Question("Onko kysymysten luonti mielestäsi helppoa?", questionnaire2, text));
			questionList2.add(new Question("Mitä mieltä olet kyselylomakkeen visuaalisesta ilmeestä?", questionnaire2, text));
			questionList2.add(new Question("Miten kehittäisit kyselylomaketta?", questionnaire2, text));
			questionList2.add(new Question("Vastaako kyselylomake tarpeitasi?", questionnaire2, checkbox, options1));
			questionList2.add(new Question("Minkä arvosanan antaisit kyselylomakkeelle? (1-5)", questionnaire2, radio, options));
					
			//Tallennetaan kysymykset kantaan
			questions.saveAll(questionList);
			questions.saveAll(questionList2);
		};
	}

}
