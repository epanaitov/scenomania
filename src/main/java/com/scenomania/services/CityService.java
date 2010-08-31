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
	public List<City> fetchByCodes(String country, String area);
	public City getById(Integer id);
	public List<City> fetchAll();
	public List<City> fetchAll(String locale);
	public List<City> fetchByArea(Integer areaId, String locale);
	public List<City> fetchAll(String locale, Double startLat, Double endLat, Double startLng, Double endLng);
}
