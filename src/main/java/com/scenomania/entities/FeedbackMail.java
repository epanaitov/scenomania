package com.scenomania.entities;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FeedbackMail {
	

	@Pattern(regexp="(?i)[A-Z0-9._%+-]+@(?i)[A-Z0-9.-]+\\.(?i)[A-Z]{2,4}", message="email.invalid")
	private String email;
	
	
	@Size.List({ @Size(min=5, message="feedback.text.short"), @Size(max=32768,  message="feedback.text.long") })
	private String text;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
