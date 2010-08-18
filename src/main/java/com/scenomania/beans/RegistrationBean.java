package com.scenomania.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.scenomania.entities.Area;
import com.scenomania.entities.Country;
import com.scenomania.services.CountryService;

@Component("register")
@Scope("request")
public class RegistrationBean {
	@Autowired
	private CountryService countryService;
	
	private Country country;
	private List<Area> areas = new ArrayList<Area>();
	
	@Autowired
	private HttpServletRequest request;
	
	public List<Country> getCountries(){
		Locale locale = RequestContextUtils.getLocale(request);		
		return countryService.fetchAll(locale.getLanguage());
	}
	

	public void areasAjax(AjaxBehaviorEvent event){
		Area ar = new Area();
		ar.setName(country.getName() + "'s Area");
		ar.setId(1);
		areas.add(ar);
	}
	
	public List<Area> getAreas() {
		return areas;
	}


	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
	
}
