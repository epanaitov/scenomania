package com.scenomania.controllers;

import com.scenomania.entities.Country;
import com.scenomania.services.CountryService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */
@Component(value="countries")
/**
 * we can consider some global scope and store all countries list in server memory
 * will work much faster than asking database each time
 * is there application sc
 */
@Scope("request") // albeit for now
public class CountriesBean {
	
	@Autowired(required=true)
	CountryService countryService;

	@Autowired(required=true)
	@Qualifier(value="messageSource")
	private ResourceBundleMessageSource messageSource;

	@Autowired(required=true)
	private HttpServletRequest request;

	private LinkedHashMap<String, Integer> all;

	public LinkedHashMap<String, Integer> getAll() {

		Locale locale = RequestContextUtils.getLocale(request);		

		List<Country> countries = countryService.fetchAll(locale.getLanguage());

		LinkedHashMap<String, Integer> result = new LinkedHashMap();
		result.put(messageSource.getMessage("select.country", null, locale), 0);
		Iterator<Country> cit = countries.iterator();

		while (cit.hasNext()) {
			Country country = cit.next();
			if (country.getId() > 2) result.put(country.getName(), country.getId());
		}

		return result;
	}

	public void setAll(Map<String, Integer> countries) {
		
	}

	public Map<String, String> selected(AjaxBehaviorEvent event) {
		return new HashMap<String, String>();
	}
}
