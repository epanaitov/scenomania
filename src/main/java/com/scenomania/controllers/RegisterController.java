package com.scenomania.controllers;

import com.scenomania.beans.SeoMetaBean;
import com.scenomania.dao.PromoterDao;
import com.scenomania.dao.UserDao;
import com.scenomania.entities.Band;
import com.scenomania.entities.BandPosition;
import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;
import com.scenomania.entities.PromoterPosition;
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
import com.scenomania.services.PromoterService;
import com.scenomania.services.UserService;
import com.scenomania.utils.PlainErrorsHashMap;
import com.scenomania.utils.UrlHelper;
import java.util.HashSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

	@Autowired(required=true)
	private HttpServletRequest request;
	
	@Autowired(required=true)
	private HttpServletResponse response;

	@Autowired(required=true)
	private HttpSession httpSession;

	@Autowired(required=true)
	private PromoterService promoterService;

	@Autowired(required=true)
	private PromoterDao promoterDao;

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String index(Model model, SeoMetaBean meta) {

		User user = new User();
		model.addAttribute("user", user);
		
		meta.setH1("Регистрация");
		
        return "register/index";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String index(@ModelAttribute("user") @Valid User user, 
			BindingResult result,
			Model model,
			HttpSession httpSession) {
		
		if (userService.getUserByEmail(user.getEmail()) != null) {
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
		
		Cookie userCookie = new Cookie("user", Integer.toString(user.getId()));
		response.addCookie(userCookie);
		
		if (request.getParameter("playin") != null) httpSession.setAttribute("playin", 1);
		if (request.getParameter("promotin") != null) httpSession.setAttribute("promotin", 1);
		return "redirect:/register/role";
	}

	@RequestMapping(value="/register/role", method=RequestMethod.GET)
	public String role(Model model) {

		if (httpSession.getAttribute("loggedin") == null) return "redirect:/register";

		if (httpSession.getAttribute("playin") != null)
			model.addAttribute("playin", true);
		if (httpSession.getAttribute("promotin") != null)
			model.addAttribute("promotin", true);

		String message = "register.roles.user_registered";
		if (httpSession.getAttribute("message") != null) message = httpSession.getAttribute("message").toString();
		model.addAttribute("message", message);

        return "register/role";
	}

	@RequestMapping(value="/register/role", method=RequestMethod.POST)
	public String rolePOST(Model model, HttpServletRequest request) {

		User loggedin = (User) httpSession.getAttribute("loggedin");
		if (loggedin == null) return "redirect:/register";


		PlainErrorsHashMap bandErrors = new PlainErrorsHashMap();
		PlainErrorsHashMap promoterErrors = new PlainErrorsHashMap();

		Boolean doBand = (request.getParameter("band_check") != null);
		Boolean doPromoter = (request.getParameter("promoter_check") != null);

		String bandName = "";
		String bandPosition = "";

		if (doBand) {

			bandName = request.getParameter("band[name]");
			if ((bandName == null) || (bandName.isEmpty())) bandErrors.addError("name", "band.name.empty");

			bandPosition = request.getParameter("band[role]");
			if ((bandPosition == null) || (bandPosition.isEmpty())) bandErrors.addError("position", "band.position.empty");
		}

		String promoterName = "";
		String promoterPosition = "";
		
		if (doPromoter) {
			promoterName = request.getParameter("promoter[name]");
			if ((promoterName == null) || (promoterName.isEmpty())) promoterErrors.addError("name", "promoter.name.empty");

			promoterPosition = request.getParameter("promoter[role]");
			if ((promoterPosition == null) || (promoterPosition.isEmpty())) promoterErrors.addError("position", "promoter.position.empty");
		}

		if (!bandErrors.isEmpty() || !promoterErrors.isEmpty()) {
			model.addAttribute("bandErrors", bandErrors);
			model.addAttribute("promoterErrors", promoterErrors);

			//if (httpSession.getAttribute("playin") != null)
			model.addAttribute("playin", true);
			//if (httpSession.getAttribute("promotin") != null)
			model.addAttribute("promotin", true);

			model.addAttribute("error", "register.roles.error_occured");

			return "register/role";
		}

		if (doBand) {

			Band band = bandService.find(bandName, loggedin.getHomecity());

			if (band == null) {
				band = new Band();
				band.setHomecity(loggedin.getHomecity());
				band.setName(bandName);
				band.setSlug(UrlHelper.getSlug(bandName));
				band.setDescription(request.getParameter("band[description]"));
				band.setMembers(new HashSet<BandPosition>());
				band = bandService.save(band);
			}

			BandPosition bandRole = new BandPosition();
			bandRole.setBand(band);
			bandRole.setUser(loggedin);
			bandRole.setPosition(bandPosition);
			band.getMembers().add(bandRole);
			band = bandService.save(band);

		}

		if (doPromoter) {

			Promoter promoter = promoterService.find(promoterName, loggedin.getHomecity());

			if (promoter == null) {
				promoter = new Promoter();
				promoter.setHomecity(loggedin.getHomecity());
				promoter.setName(promoterName);
				promoter.setStaff(new HashSet<PromoterPosition>());
				promoter = promoterDao.save(promoter);
			}

			PromoterPosition promoterRole = new PromoterPosition();
			promoterRole.setPosition(promoterPosition);
			promoterRole.setUser(loggedin);
			promoterRole.setPromoter(promoter);
			promoter.getStaff().add(promoterRole);
			promoter = promoterService.save(promoter);

		}

		httpSession.removeAttribute("playin");
		httpSession.removeAttribute("promotin");

		String message = "";
		if (doBand) message = "register.roles.band_saved";
		if (doPromoter) message = "register.roles.promoter_saved";
		if (doBand && doPromoter) message = "register.roles.band_promoter_saved";
		
		httpSession.setAttribute("message", message);

		return "redirect:/register/role";
	}
}
