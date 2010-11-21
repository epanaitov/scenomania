package com.scenomania.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eugene
 */
@Controller
public class SearchController {
	
	
	@RequestMapping(method=RequestMethod.GET, value="/search/")
	public String responseGet() {
		return "search";
	}
}
