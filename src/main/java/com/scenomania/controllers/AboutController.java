package com.scenomania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eugene
 */
@Controller
public class AboutController extends ControllerBase {

	@RequestMapping(value="/about", method=RequestMethod.GET)
	public String index() {
		return "about";
	}

}
