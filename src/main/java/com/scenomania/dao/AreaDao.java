package com.scenomania.dao;

import com.scenomania.entities.Area;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface AreaDao {
	public Area persistOrMerge(Area obj);
	public List<Area> fetchByCountryCode(String code);
	public Area getByCodes(String areaCode, String countryCode);
	public List<Area> fetchAll();
	public List<Area> fetchAll(String locale);
	public Area getById(Integer id);
}
