/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.services.impl;

import java.util.Date;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.User;
import com.scenomania.services.MailService;
import com.scenomania.services.UserService;
import com.scenomania.utils.MD5;
import com.scenomania.utils.SaltGenerator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContextUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserDao userDao;

	//@Autowired(required=true)
	//private MailService mailer;

	@Autowired(required=true)
	private HttpServletRequest request;

	@Autowired(required=true)
	@Qualifier(value="messageSource")
	private ResourceBundleMessageSource messageSource;

	@Transactional
	public User createUser(User user) {
		String salt = SaltGenerator.getSalt();

		String originalPass = user.getPassword();

		String hashedPassword = encryptPassword(user.getPassword(), salt);
		
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		user.setCreated_at(new Date());
		
		user = this.userDao.persistOrMerge(user);

		if (user != null) {
			// send an email here to the user with his login and password


			Locale locale = RequestContextUtils.getLocale(request);

			String message = messageSource.getMessage("register.mail_message", new Object[] {user.getNickname(), originalPass}, "register.mail_message", locale);

			MailService mailer = new MailServiceImpl();

			mailer.send(user.getEmail(), "testing", message);
		}

		return user;
	}

	@Transactional
	public User saveUser(User user) {
		return this.userDao.persistOrMerge(user);
	}

	@Transactional(readOnly=true)
	public User retrieveUser(Integer id) {
		return this.userDao.findById(id);
	}
	
	public static String encryptPassword(String password, String salt){
		return MD5.getHash(password + salt);
	}

	@Transactional
	public User getUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

}