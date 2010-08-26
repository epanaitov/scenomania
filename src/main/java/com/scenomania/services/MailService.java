package com.scenomania.services;

/**
 *
 * @author eugene
 */
public interface MailService {
	public void send(String recipient, String subject, String text);
}
