package com.scenomania.services;

import com.scenomania.entities.Area;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface AreaService {
	public Area saveArea(Area area);
	public List<Area> fetchByCountryCode(String code);
	public Area getByCodes(String areaCode, String countryCode);
	public List<Area> fetchAll();
	public Area getById(Integer id);
}
