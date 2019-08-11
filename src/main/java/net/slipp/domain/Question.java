package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id //USER TABLE의 키값이다 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 1씩 증가
	private Long id;
	//스프링 부트 2점 대로 업그레이드 하면서 stratege 설정 해줘야 오토인크리먼트 먹히는듯 하다
	
	@Column(nullable = false, length = 20)
	private String writer;
	private String title;
	private String contents;
	
	public Question() {
		super();
	}

	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	
}
