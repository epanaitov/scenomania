package com.nextbrainchild.tutorial;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("helloWorld")
@Scope("session")
public class HelloWorldBean {

	public String getMessage() {
		return "Hello World!";
	}
	
}
