/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.services.impl;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired(required=true)
	private UserDao userDao;

	@Transactional
	public User createUser(User user) {
		return this.userDao.persistOrMerge(user);
	}

	@Transactional(readOnly=true)
	public User retrieveUser(Integer id) {
		return this.userDao.findById(id);
	}

}