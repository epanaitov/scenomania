/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.dao;

import com.scenomania.entities.User;

public interface UserDao {

	public User findById(Integer id);
	public User persistOrMerge(User user);

}