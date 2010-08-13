package com.scenomania.services.impl;

import com.scenomania.dao.CountryDao;
import com.scenomania.entities.Area;
import com.scenomania.entities.Country;
import com.scenomania.services.CountryService;
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
public class CountryServiceImpl implements CountryService{

	@Autowired(required=true)
	private CountryDao countryDao;

	@Transactional
	public Country saveCountry(Country country) {
		return this.countryDao.persistOrMerge(country);
	}

	@Transactional
	public Country getByCode(String code) {
		return this.countryDao.getByCode(code);
	}

	@Transactional
	public List<Country> fetchAll() {
		return this.countryDao.fetchAll();
	}

	@Transactional
	public Country getbyId(Integer id) {
		return this.countryDao.findById(id);
	}
}
