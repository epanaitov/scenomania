package com.scenomania.services;

import com.scenomania.entities.City;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface CityService {
	public City saveCity(City city);
	public List<City> fetchByCountryCode(String code);
	public City getById(Integer id);
}
