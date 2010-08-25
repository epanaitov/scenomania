/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.dao.impl;

import com.scenomania.dao.AreaDao;
import com.scenomania.entities.Area;
import com.scenomania.entities.Country;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugene
 */

@Repository
public class HibernateAreaDao implements AreaDao {

	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public Area persistOrMerge(Area obj) {
		return (Area) this.sessionFactory.getCurrentSession().merge(obj);
	}

	public List<Area> fetchByCountryCode(String code) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from Area areas" + "  where country_code = ?").setParameter(0, code);
		return q.list();
	}

	public Area getByCodes(String areaCode, String countryCode) {
		return (Area) this.sessionFactory.getCurrentSession().createQuery("from Area areas where code = ? and country_code = ?").setParameter(0, areaCode).setParameter(1, countryCode).uniqueResult();
	}

	public List<Area> fetchAll() {
		return (List<Area>) this.sessionFactory.getCurrentSession().createQuery("from Area areas").list();
	}
	
	@Transactional
	public List<Area> fetchAll(String locale){
		Query q = this.sessionFactory.getCurrentSession().createQuery(
				 " from Area as a"
				+ " left join fetch c.locales as l"
				+ " where l.locale = ?"
				)
				.setParameter(0, locale)
				;
		return (List<Area>)q.list();
	}

	public Area getById(Integer id) {
		return (Area) this.sessionFactory.getCurrentSession().createQuery("from Area area where id = ?").setParameter(0, id).uniqueResult();
	}

	public List<Area> fetchByCountry(Integer countryId, String locale) {
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT "
				+ "IFNULL(al.name, a.name) as name, a.* FROM areas a "
				+ "LEFT JOIN area_locale al ON (a.id  = al.area_id) AND (al.locale = ?) "
				+ "WHERE a.country_id = ? ORDER BY name"
				).addEntity(Area.class)
				.setString(0, locale)
				.setInteger(1, countryId);

		return (List<Area>) q.list();
	}
}
