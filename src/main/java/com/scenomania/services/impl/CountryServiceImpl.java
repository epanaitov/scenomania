package com.scenomania.services.impl;

import com.scenomania.dao.CountryDao;
import com.scenomania.entities.Country;
import com.scenomania.services.CountryService;
import java.util.List;
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

	/**
	 * @todo add more code!
	 * @param code
	 * @return
	 */
	@Transactional
	public Country getByCode(String code) {
		return new Country();
	}

	@Transactional
	public List<Country> fetchAll() {
		return this.countryDao.fetchAll();
	}
}
