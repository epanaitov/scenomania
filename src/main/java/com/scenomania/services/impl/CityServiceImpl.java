package com.scenomania.services.impl;

import com.scenomania.dao.CityDao;
import com.scenomania.entities.City;
import com.scenomania.services.CityService;
import java.util.List;
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
}
