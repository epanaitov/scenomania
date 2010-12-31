package com.scenomania.beans;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author eugene
 */
@Component("UserBean")
@Scope("session")
public class UserBean {
	
	@Autowired(required=true)
	private UserService userService;
	
	private User user = null;
	
	private void setUser() {
		if (this.user == null) this.user = userService.getLogged();
	}
	
	public boolean getLogged() {
		setUser();
		return this.user != null;
	}
	
	public User getUser() {
		setUser();
		return this.user;
	}
}
