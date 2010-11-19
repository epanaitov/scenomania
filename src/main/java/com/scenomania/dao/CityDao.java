package com.scenomania.dao;

import com.scenomania.entities.City;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author eugene
 */
public interface CityDao {
	public City persistOrMerge(City obj);
	public List<City> fetchByCountryCode(String code);
	public List<City> fetchByCodes(String country, String area);
	public City getById(Integer id);
	public List<City> fetchAll();
	public List<City> fetchAll(String locale);
	public List<City> fetchByArea(Integer areaId, String locale);
	public List<City> fetchAll(String locale, Double startLat, Double endLat, Double startLng, Double endLng);
	public List<City> fetchWhere(Criterion where);
}
