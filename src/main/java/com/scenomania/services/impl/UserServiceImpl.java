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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	private String getComputerFingerprint(HttpServletRequest request) {
		ArrayList<String> sb = new ArrayList<String>();
		sb.add(request.getRemoteHost());
		
		String[] headers = {"accept-language", "user-agent", "accept-charset", "accept-encoding"};
		for (int i = 0; i< headers.length; i++) {
			Enumeration<String> header = request.getHeaders(headers[i]);
			if (header.hasMoreElements()) sb.add(StringUtils.trim(header.nextElement()));
		}
		
		return StringUtils.join(sb, '|');
	}
	
	@Transactional
	public Boolean ownsThisComputer(Integer userId, HttpServletRequest request) {

		String machineHash = getComputerFingerprint(request);
		
		return userDao.ownsThisComputer(userId, machineHash);
	}
	
	@Transactional
	public void logIn(User user, HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("scenomania_user", user.getId().toString());
		cookie.setMaxAge(60*60*24*365); // one year
		response.addCookie(cookie);
		
		userDao.assignThisComputer(user.getId(), getComputerFingerprint(request));
		
		GrantedAuthority role = new GrantedAuthorityImpl("ROLE_USER");
		List roles = new ArrayList();
		roles.add(role);
		Authentication auth = new AnonymousAuthenticationToken("somekey", user, roles);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	@Transactional
	public User getLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) return null;
		if (!auth.isAuthenticated()) return null;
		Object principal = auth.getPrincipal();
		if (principal instanceof User) return (User) principal; // consider refreshing user here
		return null; 
	}
	
}