/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scenomania.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@Autowired(required=true)
	private UserService userService;

    @RequestMapping(value="/")
    public String indexAction(Model model) {
		
        return "index";
    }

}