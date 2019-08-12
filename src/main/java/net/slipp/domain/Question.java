package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity{
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	private String title;
	@Lob
	private String contents;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
	@JsonProperty
	private Integer countOfAnswers = 0; //default 값 설정
	
	public Question() {
		super();
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	
	public void addAnswer() {
		this.countOfAnswers++;
	}
	public void deleteAnswer() {
		this.countOfAnswers--;
	}
	public void update(String title2, String contents2) {
		this.title = title2;
		this.contents = contents2;
	}
	
	public boolean isSameWriter(User loginUser) {
		//객체 의 비교는 hashCode(), equals() 오버라이드해서 선언 안하면 무조건 오류남.
		return this.writer.equals(loginUser);
	}

	@Override
	public String toString() {
		return "Question [writer=" + writer + ", title=" + title + ", contents=" + contents + ", answers=" + answers
				+ ", countOfAnswers=" + countOfAnswers + ", toString()=" + super.toString() + "]";
	}
	
}
