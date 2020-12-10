package com.example.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
	public List<Answer> findByQuestionQuestionId(Long questionId);

	@Query("SELECT " + "new com.example.kyselypalvelu.domain.AnswerStatistics(text, count(a.text))"
			+ " FROM Answer a WHERE question_id=:questionId GROUP BY a.text")
	public List <AnswerStatistics> findAnswerStatistics(@Param("questionId") Long questionId);
}
