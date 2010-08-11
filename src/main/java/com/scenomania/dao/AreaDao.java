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
}
