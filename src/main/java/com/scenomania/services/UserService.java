/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.services;

import com.scenomania.entities.User;

public interface UserService {

	public User retrieveUser(Integer id);
	public User createUser(User user);

}