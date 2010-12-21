/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.services;

import com.scenomania.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

	public User retrieveUser(Integer id);
	public User createUser(User user);
	public User saveUser(User user);
	public User getUserByEmail(String email);
	public Boolean ownsThisComputer(Integer userId, HttpServletRequest request);
	public void logIn(User user, HttpServletRequest request, HttpServletResponse response);
	public User getLogged();
}