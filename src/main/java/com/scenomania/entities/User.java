package com.scenomania.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User extends EntityBase {

	@NotNull
	@Column
	@Size.List( { @Size(min=2, message="user.nickname.short"), @Size(max=250, message="user.nickname.long") } )
    private String nickname;

	//@NotNull
	private String password;

	private String salt;

	//@NotNull
	private String email;

	
	private Date last_login;

	
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	//@Temporal(TemporalType.DATE)
	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	@Column
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
