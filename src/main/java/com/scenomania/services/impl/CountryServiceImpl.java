package com.scenomania.services.impl;

import com.scenomania.dao.CountryDao;
import com.scenomania.entities.Area;
import com.scenomania.entities.Country;
import com.scenomania.entities.CountryLocale;
import com.scenomania.services.CountryService;

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
	
	
	public List<Country> fetchAll(String locale){
		if (locale.equals("en")){
			return this.countryDao.fetchAll();
		} else {
			List<Country> countryList = this.countryDao.fetchAll(locale);
			for (Country c: countryList){
				Set<CountryLocale> cl      = c.getLocales();
				Iterator<CountryLocale> it = cl.iterator();
				if (it.hasNext()){
					String name = it.next().getName();
					if (! name.isEmpty()){
						c.setName( name );
					}
				}
			}
			return countryList;
		}
		
	}

	@Transactional
	public Country getbyId(Integer id) {
		return this.countryDao.findById(id);
	}
}
