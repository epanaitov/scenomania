package com.scenomania.controllers;

import com.scenomania.entities.Band;
import com.scenomania.entities.City;
import com.scenomania.entities.User;
import com.scenomania.services.BandService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eugene
 */
@Controller
public class AjaxBandsController {

	@Autowired(required=true)
	private BandService bandService;


	//public @ResponseBody String suggestBands(@RequestParam String query, HttpSession httpSession) {
	@RequestMapping(value="/ajax/bands/suggest/", method=RequestMethod.GET)
	public String suggestBands(@RequestParam String query, HttpSession httpSession, Model model) {

		List<Band> bands = null;

		User loggedin = (User) httpSession.getAttribute("loggedin");
		if (loggedin == null) {
			bands = bandService.suggest(query);
		} else {
			City homecity = loggedin.getHomecity();

			bands = bandService.suggest(query, homecity.getId());
		}

		model.addAttribute("bands", bands);

		return "ajax/bands/suggest";
	}
}
