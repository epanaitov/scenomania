package com.scenomania.controllers;


import com.google.gson.Gson;
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
import com.scenomania.utils.CityOnMap;
import java.util.ArrayList;

@Controller
public class AjaxMapCitiesController {
	
	@Autowired(required=true)
	CityService cityService;
	
	@RequestMapping(value="/ajax/map/cities/", method=RequestMethod.GET)
	public ResponseEntity<String> getCities(
											@RequestParam(defaultValue="0.0") Double north,
											@RequestParam(defaultValue="0.0") Double east,
											@RequestParam(defaultValue="0.0") Double south,
											@RequestParam(defaultValue="0.0") Double west,
											HttpServletRequest request){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/plain; charset=utf-8");
		
		if ((Double.compare(west, east) == 0) || (Double.compare(south, north) == 0)) return new ResponseEntity<String>("[]", responseHeaders, HttpStatus.CREATED);
		
		Locale locale      = RequestContextUtils.getLocale(request);
		StringBuilder html = new StringBuilder();
		
		List<City> cities = cityService.fetchAll(locale.getLanguage(), south, north, west, east);
		Iterator<City> it = cities.iterator();
		
		//html.append("{\"cities\":[");
		//boolean f = true; 
		
		List<CityOnMap> list = new ArrayList<CityOnMap>();
		
		while (it.hasNext()){
			
			City city = it.next();
			
			if (city.getArea() == null) continue;
			if (city.getArea().getCountry() == null) continue;
			
			CityOnMap com = new CityOnMap();
			com.id = city.getId();
			com.name = city.getName();
			com.lat = city.getLatitude();
			com.lng = city.getLongitude();
			com.countrySlug = city.getArea().getCountry().getSlug();
			com.areaCode = city.getArea().getCode();
			com.pop = city.getPopulation();
			com.slug = city.getSlug();
			
			list.add(com);
			
		}
		
		
		
		Gson gson = new Gson();
		return new ResponseEntity<String>(gson.toJson(list), responseHeaders, HttpStatus.CREATED);
	}
}
