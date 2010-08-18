package com.scenomania.services.impl;

import com.scenomania.dao.AreaDao;
import com.scenomania.entities.Area;
import com.scenomania.entities.AreaLocale;
import com.scenomania.entities.Country;
import com.scenomania.entities.CountryLocale;
import com.scenomania.services.AreaService;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	public List<Area> fetchAll(String locale){
		if (locale.equals("en")){
			return this.areaDao.fetchAll();
		} else {
			List<Area> areaList = this.areaDao.fetchAll(locale);
			for (Area a: areaList){
				Set<AreaLocale> al      = a.getLocales();
				Iterator<AreaLocale> it = al.iterator();
				if (it.hasNext()){
					String name = it.next().getName();
					if (! name.isEmpty()){
						a.setName( name );
					}
				}
			}
			return areaList;
		}
	}

	@Transactional
	public Area getById(Integer id) {
		return this.areaDao.getById(id);
	}
}
