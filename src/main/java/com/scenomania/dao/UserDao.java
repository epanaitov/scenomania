package com.scenomania.dao;

import com.scenomania.entities.User;

// will work since september 10
//public interface UserDao extends DaoBase<User> {
public interface UserDao {

	public User findById(Integer id);
	public User persistOrMerge(User obj);

	public User findByEmail(String email);
	
}