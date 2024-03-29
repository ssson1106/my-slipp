package net.slipp.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
	@Id //USER TABLE의 키값이다 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 1씩 증가
	@JsonProperty //get 메소드 없이도 json 객체로 rest 리턴 되도록하는 설정
	private Long id;
	//스프링 부트 2점 대로 업그레이드 하면서 stratege 설정 해줘야 오토인크리먼트 먹히는듯 하다
	
	@CreatedDate
	@JsonProperty
	private LocalDateTime createDate;
	
	@LastModifiedDate
	@JsonProperty
	private LocalDateTime modifiedDate;
	
	public boolean matchId(Long newId) {
		if(newId == null) 
			return false;
		return this.id.equals(newId);
		
	}

	public AbstractEntity() {
		super();
	}

	public AbstractEntity(Long id, LocalDateTime createDate, LocalDateTime modifiedDate) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
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
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getFormattedCreateDate() {
		return getFormattedDate(createDate, "yyyy.MM.dd HH:mm:ss");
	}
	public String getFormattedModifiedDate() {
		return getFormattedDate(modifiedDate, "yyyy.MM.dd HH:mm:ss");
	}
	private String getFormattedDate(LocalDateTime time, String format) {
		if (time == null) {
			return "";
		}
		return time.format(DateTimeFormatter.ofPattern(format));
	}
}
