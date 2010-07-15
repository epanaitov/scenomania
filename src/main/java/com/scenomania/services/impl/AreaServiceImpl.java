package com.scenomania.services.impl;

import com.scenomania.dao.AreaDao;
import com.scenomania.entities.Area;
import com.scenomania.services.AreaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugene
 */

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired(required=true)
	private AreaDao areaDao;

	@Transactional
	public Area saveArea(Area area) {
		return areaDao.persistOrMerge(area);
	}

	@Transactional
	public List<Area> fetchByCountryCode(String code) {
		return this.areaDao.fetchByCountryCode(code);
	}

	@Transactional
	public Area getByCodes(String areaCode, String countryCode) {
		return this.areaDao.getByCodes(areaCode, countryCode);
	}

	@Transactional
	public List<Area> fetchAll() {
		return this.areaDao.fetchAll();
	}

	@Transactional
	public Area getById(Integer id) {
		return this.areaDao.getById(id);
	}
}
