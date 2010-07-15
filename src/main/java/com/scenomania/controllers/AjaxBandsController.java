package com.scenomania.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author eugene
 */
public class AjaxBandsController {


	@RequestMapping(value="/ajax/bands/suggest/", method=RequestMethod.GET)
	public @ResponseBody String suggestBands(@RequestParam String query, HttpSession httpSession) {

		

		return "";
	}
}
