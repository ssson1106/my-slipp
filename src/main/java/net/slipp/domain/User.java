package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id //USER TABLE의 키값이다 선언
	@GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에서 자동으로 1씩 증가
	private Long id;
	//스프링 부트 2점 대로 업그레이드 하면서 stratege 설정 해줘야 오토인크리먼트 먹히는듯 하다
	
	@Column(nullable = false, length = 20, unique=true)
	private String userId;
	private String password;
	private String name;
	private String email;
	
	public void update(User updateUser) {
		this.password = updateUser.getPassword();
		this.name = updateUser.getName();
		this.email = updateUser.getEmail();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public boolean matchId(Long newId) {
		if(newId == null) 
			return false;
		return this.id.equals(newId);
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email="
				+ email + "]";
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
