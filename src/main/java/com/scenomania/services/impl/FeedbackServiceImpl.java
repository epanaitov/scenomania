package com.scenomania.services.impl;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.scenomania.services.FeedbackService;


@Service
public class FeedbackServiceImpl implements FeedbackService {

	private MailSender mailSender;
	private SimpleMailMessage templateMessage;
	
	@Override
	public void send(String backmail, String text) {
		
		SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
		msg.setFrom(backmail);
		msg.setText(text);
		try {
			this.mailSender.send(msg);
		} catch (MailException ex){
			Logger.getLogger(FeedbackServiceImpl.class).error("MailException " + msg.getFrom(), ex);
		}
		
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}

	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}

	
}
