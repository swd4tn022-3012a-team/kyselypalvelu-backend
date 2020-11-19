package com.example.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	List<Answer> findByQuestionQuestionId(Long questionId);
}
