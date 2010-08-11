package com.scenomania.services;

import com.scenomania.entities.Country;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface CountryService {
	public Country saveCountry(Country country);
	public Country getByCode(String code);
	public List<Country> fetchAll();
}
