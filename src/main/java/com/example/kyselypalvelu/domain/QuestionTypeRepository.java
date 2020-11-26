package com.example.kyselypalvelu.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;

public interface QuestionTypeRepository extends CrudRepository<QuestionType, Long> {
	
}
 