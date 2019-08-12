package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity {
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty 
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	@JsonProperty 
	private Question question;
	
	@Lob
	@JsonProperty 
	private String contents;

	public Answer() {
		super();
	}

	public Answer(User writer, Question question, String contents) {
		super();
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}
	
	public void update(String contents2) {
		this.contents = contents2;
	}

	@Override
	public String toString() {
		return "Answer [writer=" + writer + ", question=" + question + ", contents=" + contents + ", toString()="
				+ super.toString() + "]";
	}

	public boolean isSameWriter(User loginUser) {
		//객체 의 비교는 hashCode(), equals() 오버라이드해서 선언 안하면 무조건 오류남.
		return this.writer.equals(loginUser);
	}

}
