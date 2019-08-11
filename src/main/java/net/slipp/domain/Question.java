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

@Entity
public class Question {
	@Id //USER TABLE의 키값이다 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 1씩 증가
	private Long id;
	//스프링 부트 2점 대로 업그레이드 하면서 stratege 설정 해줘야 오토인크리먼트 먹히는듯 하다
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	private String title;
	@Lob
	private String contents;
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
	public Question() {
		super();
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now(); //현재 시간 할당
	}
	
	public String getFormattedCreateDate() {
		if (createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
		
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
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}
	
}
