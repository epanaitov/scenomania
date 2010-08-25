package com.scenomania.services;

import com.scenomania.entities.Area;
import java.util.List;
import java.util.Set;

/**
 *
 * @author eugene
 */
public interface AreaService {
	public Area saveArea(Area area);
	public List<Area> fetchByCountryCode(String code);
	public Area getByCodes(String areaCode, String countryCode);
	public List<Area> fetchAll();
	public List<Area> fetchAll(String locale);
	public Area getById(Integer id);
	public List<Area> fetchByCountry(Integer countryId, String locale);
}
