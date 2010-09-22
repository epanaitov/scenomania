package com.scenomania.services;

import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;

/**
 *
 * @author eugene
 */
public interface PromoterService {
	public Promoter find(String name, City city);
	public Promoter save(Promoter promoter);
}
