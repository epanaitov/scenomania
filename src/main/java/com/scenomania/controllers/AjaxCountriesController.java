package com.scenomania.controllers;

import com.google.gson.Gson;
import com.scenomania.entities.Area;
import com.scenomania.entities.City;
import com.scenomania.services.AreaService;
import com.scenomania.services.CityService;
import com.scenomania.services.CountryService;
import com.scenomania.utils.DojoDataSource;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired(required=true)
	@Qualifier(value="messageSource")
	private ResourceBundleMessageSource messageSource;

	@Autowired(required=true)
	private HttpServletRequest request;

	@RequestMapping(value="/ajax/countries/get_areas/", method=RequestMethod.GET)
	public ResponseEntity<String> getAareas(@RequestParam Integer id, HttpServletRequest request) {

		Locale locale = RequestContextUtils.getLocale(request);

		DojoDataSource dataSource = new DojoDataSource();

		//dataSource.addItem("0", messageSource.getMessage("select.area", null, locale));

		Iterator<Area> ait = areaService.fetchByCountry(id, locale.getLanguage()).iterator();
		while (ait.hasNext()) {
			Area area = ait.next();
			dataSource.addItem(Integer.toString(area.getId()), area.getName());
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");

		Gson gson = new Gson();

		return new ResponseEntity<String>(gson.toJson(dataSource, DojoDataSource.class), responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/ajax/countries/get_cities/", method=RequestMethod.GET)
	public ResponseEntity<String> getCities(@RequestParam Integer area, HttpServletRequest request) {
		String html = "";

		Locale locale = RequestContextUtils.getLocale(request);

		DojoDataSource dataSource = new DojoDataSource();

		//dataSource.addItem("0", messageSource.getMessage("select.city", null, locale));

		Iterator<City> cit = cityService.fetchByArea(area, locale.getLanguage()).iterator();
		while (cit.hasNext()) {
			City city = cit.next();
			dataSource.addItem(Integer.toString(city.getId()), city.getName());
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		Gson gson = new Gson();
		return new ResponseEntity<String>(gson.toJson(dataSource, DojoDataSource.class), responseHeaders, HttpStatus.CREATED);
	}

}
