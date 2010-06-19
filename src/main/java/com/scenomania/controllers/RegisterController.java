package com.scenomania.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scenomania.entities.User;
import com.scenomania.services.UserService;

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
	public String index(@ModelAttribute("user") @Valid User user, BindingResult result, Model model,
			HttpServletRequest request) {
		
		String passwordConfirm = request.getParameter("password_confirm");
		if (passwordConfirm == null || !user.getPassword().equals(passwordConfirm)){
			result.rejectValue("password", "password.confirm_error", "user.password.confirm_error");
		}
		
		if (userService.getUserByEmail(user.getEmail()) != null){
			result.rejectValue("email", "email.user_exists", "user.email.user_exists");
		}
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("userErrors", hashErrors(result.getFieldErrors()));
			return "register/index";
		}
		
		userService.createUser(user);
		return "redirect:/";
	}
}
