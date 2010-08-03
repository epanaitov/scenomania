package com.scenomania.services;

import com.scenomania.entities.Band;
import com.scenomania.entities.City;
import java.util.List;

/**
 *
 * @author eugene
 */
public interface BandService {
	public Band findById(Integer id);
	public Band save(Band band);
	public List<Band> suggest(String query);
	public List<Band> suggest(String query, Integer cityId);
	public Band find(String name, City homecity);
}
