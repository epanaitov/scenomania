package com.scenomania.services.impl;

import com.scenomania.dao.BandDao;
import com.scenomania.entities.Band;
import com.scenomania.entities.City;
import com.scenomania.services.BandService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugene
 */

@Service
public class BandServiceImpl implements BandService {

	@Autowired
	private BandDao bandDao;

	@Transactional
	public Band findById(Integer id) {
		return this.bandDao.findById(id);
	}

	@Transactional
	public Band save(Band band) {
		return this.bandDao.save(band);
	}

	@Transactional
	public List<Band> suggest(String query) {
		HashMap params = new HashMap();
		params.put("query", query);
		return bandDao.suggest(params);
	}

	@Transactional
	public List<Band> suggest(String query, Integer cityId) {
		HashMap params = new HashMap();
		//params.put("query", query);
		//params.put("city_id", cityId);
		return bandDao.suggest(params);
	}

	@Transactional
	public Band find(String name, City homecity) {
		HashMap params = new HashMap();
		params.put("name", name);
		params.put("homecity", homecity);
		return bandDao.getWhere(params);
	}
}
