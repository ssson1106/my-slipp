package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepostory extends JpaRepository<Question, Long>{

	void save(Answer newAnswer);

}