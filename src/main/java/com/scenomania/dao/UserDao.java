package com.scenomania.dao;

import com.scenomania.entities.User;

public interface UserDao {

	public User findById(Integer id);
	public User persistOrMerge(User user);
	public User findByEmail(String email);
	
}