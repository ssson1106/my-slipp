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

@Entity
public class Answer {
	@Id //USER TABLE의 키값이다 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 1씩 증가
	private Long id;
	//스프링 부트 2점 대로 업그레이드 하면서 stratege 설정 해줘야 오토인크리먼트 먹히는듯 하다
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	private Question question;
	
	@Lob
	private String contents;
	private LocalDateTime createDate;

	public Answer() {
		super();
	}

	public Answer(User writer, Question question, String contents) {
		super();
		this.writer = writer;
		this.question = question;
		this.contents = contents;
		this.createDate = LocalDateTime.now(); //현재 시간 할당
	}
	
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
		
	}

	public void update(String contents2) {
		this.contents = contents2;
	}
	
	
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", question=" + question + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}

	public boolean isSameWriter(User loginUser) {
		//객체 의 비교는 hashCode(), equals() 오버라이드해서 선언 안하면 무조건 오류남.
		return this.writer.equals(loginUser);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
