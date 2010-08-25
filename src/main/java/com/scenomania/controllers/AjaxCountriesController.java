package com.scenomania.controllers;

import com.scenomania.entities.Area;
import com.scenomania.entities.City;
import com.scenomania.services.AreaService;
import com.scenomania.services.CityService;
import com.scenomania.services.CountryService;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */

@Controller
public class AjaxCountriesController {

	@Autowired(required=true)
	AreaService areaService;

	@Autowired(required=true)
	CityService cityService;

	@Autowired(required=true)
	CountryService countryService;

	@RequestMapping(value="/ajax/countries/get_areas/", method=RequestMethod.GET)
	public ResponseEntity<String> getAareas(@RequestParam Integer id, HttpServletRequest request) {
		String html = "";
		html+= "<option value=\"0\">--------------------</option>\r\n";

		Locale locale = RequestContextUtils.getLocale(request);		

		Iterator<Area> ait = areaService.fetchByCountry(id, locale.getLanguage()).iterator();
		while (ait.hasNext()) {
			Area area = ait.next();
			html+= "<option value=\""+area.getId()+"\">"+area.getName()+"</option>\r\n";
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		return new ResponseEntity<String>(html, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/ajax/countries/get_cities/", method=RequestMethod.GET)
	public ResponseEntity<String> getCities(@RequestParam Integer area, HttpServletRequest request) {
		String html = "";

		html+= "<option value=\"0\">--------------------</option>\r\n";

		Locale locale = RequestContextUtils.getLocale(request);		

		Iterator<City> cit = cityService.fetchByArea(area, locale.getLanguage()).iterator();
		while (cit.hasNext()) {
			City city = cit.next();
			html+= "<option value=\""+city.getId()+"\">"+city.getName()+"</option>\r\n";
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		return new ResponseEntity<String>(html, responseHeaders, HttpStatus.CREATED);
	}

}
