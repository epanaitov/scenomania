package com.scenomania.controllers;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.Band;
import com.scenomania.entities.BandPosition;
import com.scenomania.entities.City;
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
import com.scenomania.services.BandService;
import com.scenomania.services.CityService;
import com.scenomania.services.UserService;
import com.scenomania.utils.PlainErrorsHashMap;
import java.util.HashSet;
import java.util.Set;
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

	@Autowired(required=true)
	private BandService bandService;

	@Autowired(required=true)
	private UserDao userDao;


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
		return "redirect:/register/role";
	}

	@RequestMapping(value="/register/role", method=RequestMethod.GET)
	public String role(Model model, HttpSession httpSession) {

		User loggedin = (User) httpSession.getAttribute("loggedin");
		if (loggedin == null) return "redirect:/register";

		loggedin = userDao.refresh(loggedin);

		Set<BandPosition> playsIn = loggedin.getPlayingIn();

		if ((playsIn != null) && (!playsIn.isEmpty())) {
			model.addAttribute("message", "band.saved");
		}

        return "register/role";
	}

	@RequestMapping(value="/register/role", method=RequestMethod.POST)
	public String role(Model model,
			HttpServletRequest request,
			HttpSession httpSession) {

		User loggedin = (User) httpSession.getAttribute("loggedin");
		if (loggedin == null) return "redirect:/register";

		String role = request.getParameter("role");

		if (role.equals("band")) {

			//Map<String, LinkedList> errorsHash = new HashMap<String, LinkedList>();
			PlainErrorsHashMap errorsHash = new PlainErrorsHashMap();

			String name = request.getParameter("band-name");
			if ((name == null) || (name.isEmpty())) errorsHash.addError("band-name", "band.name.empty");

			String position = request.getParameter("band-position");
			if ((position == null) || (position.isEmpty())) errorsHash.addError("band-position", "band.position.empty");

			if (!errorsHash.isEmpty()) {
				model.addAttribute("bandErrors", errorsHash);
				return "register/role";
			}

			Band band = bandService.find(name, loggedin.getHomecity());

			if (band == null) {
				band = new Band();
				band.setHomecity(loggedin.getHomecity());
				band.setName(name);
				band.setDescription(request.getParameter("band-description"));
				band.setMembers(new HashSet<BandPosition>());
				band = bandService.save(band);
			}

			BandPosition bandPosition = new BandPosition();
			bandPosition.setBand(band);
			bandPosition.setUser(loggedin);
			bandPosition.setPosition(position);
			band.getMembers().add(bandPosition);
			band = bandService.save(band);

			return "redirect:/register/role";
		}

		if (role.equals("promoter")) {
			
		}

 		return "register/role";
	}
}
