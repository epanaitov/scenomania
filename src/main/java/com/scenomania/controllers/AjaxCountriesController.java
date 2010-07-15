package com.scenomania.controllers;

import com.scenomania.entities.Area;
import com.scenomania.entities.City;
import com.scenomania.entities.Country;
import com.scenomania.services.AreaService;
import com.scenomania.services.CityService;
import com.scenomania.services.CountryService;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody String getAareas(@RequestParam Integer id) {
		String html = "";
		html+= "<option value=\"0\">--------------------</option>\r\n";
		
		Iterator<Area> ait = countryService.getbyId(id).getAreas().iterator();
		while (ait.hasNext()) {
			Area area = ait.next();
			html+= "<option value=\""+area.getId()+"\">"+area.getName()+"</option>\r\n";
		}

		return html;
	}

	@RequestMapping(value="/ajax/countries/get_cities/", method=RequestMethod.GET)
	public @ResponseBody String getCities(@RequestParam Integer area) {
		String html = "";

		html+= "<option value=\"0\">--------------------</option>\r\n";

		Iterator<City> cit = areaService.getById(area).getCities().iterator();
		while (cit.hasNext()) {
			City city = cit.next();
			html+= "<option value=\""+city.getId()+"\">"+city.getName()+"</option>\r\n";
		}

		return html;
	}

}
