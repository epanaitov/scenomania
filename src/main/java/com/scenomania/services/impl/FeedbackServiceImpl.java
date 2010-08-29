package com.scenomania.services.impl;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.scenomania.services.FeedbackService;
import com.scenomania.services.MailService;


@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Override
	public void send(String backmail, String text) {

		MailService mailer = new MailServiceImpl();

		mailer.send("eugene.panaitov@gmail.com", "tourageo.us feedback form", text, backmail);
		
	}

}
