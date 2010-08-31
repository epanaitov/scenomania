package com.scenomania.controllers;


import java.util.Iterator;
import java.util.List;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import com.scenomania.entities.City;
import com.scenomania.services.CityService;

@Controller
public class AjaxMapCitiesController {
	
	@Autowired(required=true)
	CityService cityService;
	
	@RequestMapping(value="/ajax/map/cities/", method=RequestMethod.GET)
	public ResponseEntity<String> getCities(
											@RequestParam Double north,
											@RequestParam Double east,
											@RequestParam Double south,
											@RequestParam Double west,
											HttpServletRequest request){
		
		Locale locale      = RequestContextUtils.getLocale(request);
		StringBuilder html = new StringBuilder();
		
		List<City> cities = cityService.fetchAll(locale.getLanguage(), south, north, west, east);
		Iterator<City> it = cities.iterator();
		
		html.append("{\"cities\":[");
		boolean f = true; 
		while (it.hasNext()){
			City city = it.next();
			if (f){
				f = false;
			} else {
				html.append(",");
			}
			html.append(
					"{"
					+ "\"id\":" + city.getId()
					+ ",\"lat\":" + city.getLatitude()
					+ ",\"lng\":" + city.getLongitude()
					+ ",\"pop\":" + city.getPopulation()
					+ ",\"name\":\"" + city.getName().replace("\"", "\\\"") + "\""
					+ "}"
			
			);
			
		}
		html.append("]}");
		
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/plain; charset=utf-8");
		
		return new ResponseEntity<String>(html.toString(), responseHeaders, HttpStatus.CREATED);
	}
}
