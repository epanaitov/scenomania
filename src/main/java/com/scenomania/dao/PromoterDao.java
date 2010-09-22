package com.scenomania.dao;

import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;
import java.util.Locale;

/**
 *
 * @author eugene
 */
public interface PromoterDao {
	public Promoter find(String name, Locale locale, City city);
	public Promoter save(Promoter promoter);
}
