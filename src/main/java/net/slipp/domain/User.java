package net.slipp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User extends AbstractEntity {
	
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
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", toString()=" + super.toString() + "]";
	}
}
