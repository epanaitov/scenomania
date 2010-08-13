package com.scenomania.controllers;

import com.scenomania.entities.Country;
import com.scenomania.services.CountryService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class CountriesBean {
	
	@Autowired(required=true)
	CountryService countryService;

	private LinkedHashMap<String, Integer> all;

	public LinkedHashMap<String, Integer> getAll() {
		List<Country> countries = countryService.fetchAll();

		LinkedHashMap<String, Integer> result = new LinkedHashMap();
		result.put("-------------", 0);
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
