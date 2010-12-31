package com.scenomania.controllers;

import com.scenomania.beans.SeoMetaBean;
import com.scenomania.beans.SeoPathBean;
import com.scenomania.dao.CityDao;
import com.scenomania.entities.AreaLocale;
import com.scenomania.entities.City;
import com.scenomania.entities.CityLocale;
import com.scenomania.entities.CountryLocale;
import com.scenomania.utils.LocaleHelper;
import java.util.Iterator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */
@Controller
public class CitiesController extends ControllerBase {
	
	@Autowired(required=true)
	private CityDao cityDao;
	
	@RequestMapping(method = RequestMethod.GET, value="/{country}/{area}/{city}-{id}/")
	public String responseGet(
					@PathVariable("country") String country_slug, 
					@PathVariable("area") String area_slug, 
					@PathVariable("city") String city_slug,
					@PathVariable("id") Integer city_id,
					SeoMetaBean meta,
					Model model
					) {
		
		City city = cityDao.getById(city_id);
		
		if (city == null) return "redirect:/";
		
		if (city.getArea() == null)  {
			// city without an area
			// handle it later 
			return "redirect:/";
		}
		
		if (
			!city.getArea().getCode().equals(area_slug) 
			|| !city.getSlug().equals(city_slug) 
			|| !city.getArea().getCountry().getSlug().equals(country_slug)
			) return "redirect:/"+city.getArea().getCountry().getSlug()+"/"+city.getArea().getCode()+"/"+city.getSlug()+"-"+Integer.toString(city_id)+"/";
		
		
		Locale locale = RequestContextUtils.getLocale(request);
		
		String city_name = city.getName();
		CityLocale cl = LocaleHelper.getLocale(city, request);
		if (cl != null) city_name = cl.getName();
		
		String area_name = city.getArea().getName();
		AreaLocale al = LocaleHelper.getLocale(city.getArea(), request);
		if (al != null) area_name = al.getName();
		
		String country_name = city.getArea().getCountry().getName();
		CountryLocale kl = LocaleHelper.getLocale(city.getArea().getCountry(), request);
		if (kl != null) country_name = kl.getName();
		
		meta.setTitle("Группы, концерты, организаторы, сцены, "+city_name+", "+country_name);
		meta.setKeywords("группы, концерты, организаторы, туры, сцены, гастроли, "+city_name+", "+country_name);
		meta.setDescription("Ищешь где выступить в "+city_name+", "+country_name+"? Нужны свежие группы из "+city_name+", "+country_name+"? Интересуешься концертами в "+city_name+", "+country_name+"? Сценомания расскажет о группах, организаторах и концертах в "+city_name+", "+country_name+".");
		meta.setH1("Группы и сцены в "+city_name+", "+country_name);
		
		path.setLength(0);
		path.addCrumb(city.getArea().getCountry());
		path.addCrumb(city.getArea());
		path.addCrumb(city);
		
		model.addAttribute("city_name", city_name);
		model.addAttribute("area_name", area_name);
		model.addAttribute("country_name", country_name);
		
		model.addAttribute("bands", city.getBands());
		model.addAttribute("promoters", city.getPromoters());
		
		return "city";
	}
			
	
}
