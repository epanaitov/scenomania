package com.scenomania.controllers;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author eugene
 *
 */

@Controller
public class RegisterController {
	@Autowired(required=true)
	private UserService userService;

	//private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getRegister(Model model) {

		//User user = new User();
		User user = userService.retrieveUser(1);
		model.addAttribute("user", user);
		
        return "register/index";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String postRegister(@ModelAttribute("user") User user, BindingResult result) {
		/*
		Assert.notNull(user, "User must be provided.");
		for (ConstraintViolation<User> constraint : validator.validate(user)) {
			result.rejectValue(constraint.getPropertyPath(), null, constraint.getMessage());
		}
		*/
		
		if (result.hasErrors()) {
			return "register/index";
		}
		
		// create user here
		
		return "redirect:/register";
	}
}
