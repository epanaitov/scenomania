package com.scenomania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eugene
 */
@Controller
public class TeamController extends ControllerBase {

	@RequestMapping(value="/team", method=RequestMethod.GET)
	public String index() {
		return "team";
	}

}
