package com.scenomania.services.impl;

import com.scenomania.services.MailService;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugene
 */
@Service
public class MailServiceImpl implements MailService {

	private void sendmail(String recipient, String subject, String text, String from) {

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("localhost");
		sender.setPort(25);
		sender.setDefaultEncoding("utf-8");

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(recipient);
			helper.setText(text);
			helper.setSubject(subject);
			helper.setFrom(from);
			sender.send(message);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void send(String recipient, String subject, String text) {
		sendmail(recipient, subject, text, "mailer@tourageo.us");
	}

	public void send(String recipient, String subject, String text, String from) {
		sendmail(recipient, subject, text, from);
	}
}
