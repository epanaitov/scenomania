/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class IndexController {

	@Autowired(required=true)
	private UserService userService;

    @RequestMapping("/")
    public ModelAndView indexAction() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("message", "Hello World!");
		
		User user = userService.retrieveUser(1);
		mav.addObject("user", user);
		
        return mav;
    }

	@RequestMapping("/xyu")
    public ModelAndView xuyAction() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("xyu");
        mav.addObject("message", "Hello World!");
        return mav;
    }
}