package com.scenomania.services.impl;

import com.scenomania.dao.CityDao;
import com.scenomania.entities.City;
import com.scenomania.entities.CityLocale;
import com.scenomania.services.AreaService;
import com.scenomania.services.CityService;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugene
 */

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Autowired
	private AreaService areaService;

	@Transactional
	public City saveCity(City city) {
		return this.cityDao.persistOrMerge(city);
	}

	@Transactional
	public List<City> fetchByCountryCode(String code) {
		return this.cityDao.fetchByCountryCode(code);
	}

	@Transactional
	public City getById(Integer id) {
		return this.cityDao.getById(id);
	}

	@Transactional
	public List<City> fetchByCodes(String country, String area) {
		return this.cityDao.fetchByCodes(country, area);
	}

	@Transactional
	public List<City> fetchAll() {
		return this.cityDao.fetchAll();
	}
	
	public List<City> fetchAll(String locale){
		if (locale.equals("en")){
			return this.cityDao.fetchAll();
		} else {
			List<City> cityList = this.cityDao.fetchAll(locale);
			for (City c: cityList){
				Set<CityLocale> cl      = c.getLocales();
				Iterator<CityLocale> it = cl.iterator();
				if (it.hasNext()){
					String name = it.next().getName();
					if (! name.isEmpty()){
						c.setName( name );
					}
				}
			}
			return cityList;
		}
		
	}

	public List<City> fetchByArea(Integer areaId, String locale) {
		if (locale.equals("en")){
			return new ArrayList<City> (this.areaService.getById(areaId).getCities());
		} else {
			return this.cityDao.fetchByArea(areaId, locale);
		}
	}
}
