package com.scenomania.dao;

import com.scenomania.entities.Country;
import java.util.List;

/**
 *
 * @author eugene
 */
//public interface CountryDao extends DaoBase<Country> {
public interface CountryDao {

	public Country findById(Integer id);
	public Country persistOrMerge(Country obj);
	public List<Country> fetchAll();
	public Country getByCode(String code);

}
