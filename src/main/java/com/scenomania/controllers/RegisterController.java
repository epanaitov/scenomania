package com.scenomania.controllers;

import com.scenomania.entities.Band;
import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;
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
import com.scenomania.services.CityService;
import com.scenomania.services.UserService;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eugene
 *
 */

@Controller
public class RegisterController extends ControllerBase {
	@Autowired(required=true)
	private UserService userService;

	@Autowired(required=true)
	private CityService cityService;


	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String index(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		
        return "register/index";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String index(@ModelAttribute("user") @Valid User user, 
			BindingResult result,
			Model model,
			HttpServletRequest request,
			HttpSession httpSession) {
		
		String passwordConfirm = request.getParameter("password_confirm");
		if (passwordConfirm == null || !user.getPassword().equals(passwordConfirm)){
			result.rejectValue("password", "password.confirm_error", "user.password.confirm_error");
		}
		
		if (userService.getUserByEmail(user.getEmail()) != null){
			result.rejectValue("email", "email.user_exists", "user.email.user_exists");
		}

		Integer homecityId = 0;
		try {
			homecityId = Integer.parseInt(request.getParameter("city_select"));

			City homecity = cityService.getById(homecityId);

			if (homecity == null) result.rejectValue("homecity", "city.not_exist", "city.not_exist");
			else user.setHomecity(homecity);

		} catch (Exception e) {
			result.rejectValue("homecity", "city.not_selected", "city.not_selected");
		}
		
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("userErrors", hashErrors(result.getFieldErrors()));
			return "register/index";
		}
		
		user = userService.createUser(user);
		httpSession.setAttribute("loggedin", user);
		return "redirect:/register/roles";
	}

	@RequestMapping(value="/register/roles", method=RequestMethod.GET)
	public String roles(Model model) {

		Band band = new Band();
		Promoter promoter = new Promoter();

		model.addAttribute("band", band);
		model.addAttribute("promoter", promoter);

        return "register/roles";
	}
}
