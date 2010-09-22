package com.scenomania.controllers;

import com.google.gson.Gson;
import com.scenomania.dao.BandDao;
import com.scenomania.dao.UserDao;
import com.scenomania.entities.Band;
import com.scenomania.entities.User;
import com.scenomania.services.BandService;
import com.scenomania.utils.DojoDataSource;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired(required=true)
	private HttpSession httpSession;

	@Autowired(required=true)
	private UserDao userDao;

	@Autowired(required=true)
	private BandDao bandDao;


	//public @ResponseBody String suggestBands(@RequestParam String query, HttpSession httpSession) {
	@RequestMapping(value="/ajax/bands/suggest/", method=RequestMethod.GET)
	public ResponseEntity<String> suggestBands() {

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");

		User loggedin = (User) httpSession.getAttribute("loggedin");
		//if (loggedin == null) return new ResponseEntity<String>("no user logged", responseHeaders, HttpStatus.CREATED);

		//loggedin = userDao.refresh(loggedin);

		DojoDataSource dataSource = new DojoDataSource();

		//Iterator<Band> bit = loggedin.getHomecity().getBands().iterator();
		Iterator<Band> bit = bandDao.fetchAll().iterator();	
		
		while (bit.hasNext()) {
			Band band = bit.next();
			dataSource.addItem(Integer.toString(band.getId()), band.getName());
		}
			
		Gson gson = new Gson();
		return new ResponseEntity<String>(gson.toJson(dataSource, DojoDataSource.class), responseHeaders, HttpStatus.CREATED);
	}
}
