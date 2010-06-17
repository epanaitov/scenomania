/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.services.impl;

import java.util.Date;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import com.scenomania.utils.MD5;
import com.scenomania.utils.SaltGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserDao userDao;

	@Transactional
	public User createUser(User user) {
		String salt = SaltGenerator.getSalt();
		String hashedPassword = encryptPassword(user.getPassword(), salt);
		
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		user.setCreated_at(new Date());
		
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