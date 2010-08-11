package com.scenomania.dao;

import com.scenomania.entities.City;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface CityDao {
	public City persistOrMerge(City obj);
	public List<City> fetchByCountryCode(String code);
	public City getById(Integer id);
}
