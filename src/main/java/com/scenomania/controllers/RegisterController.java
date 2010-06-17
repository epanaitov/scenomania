package com.scenomania.controllers;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author eugene
 *
 */

@Controller
public class RegisterController extends ControllerBase {
	@Autowired(required=true)
	private UserService userService;

	//private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String index(Model model) {

		//User user = new User();
		User user = userService.retrieveUser(1);
		model.addAttribute("user", user);
		
        return "register/index";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String index(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("userErrors", hashErrors(result.getFieldErrors()));
			return "register/index";
		}
		
		// create user here
		
		return "redirect:/";
	}
}
