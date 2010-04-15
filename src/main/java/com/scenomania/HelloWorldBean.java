package com.scenomania;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("helloWorld")
@Scope("session")
public class HelloWorldBean {

	public String getMessage() {
		return java.util.ResourceBundle.getBundle("com/scenomania/messages").getString("HELLO WORLD!");
	}
	
}
