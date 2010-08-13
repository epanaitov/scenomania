/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.dao.impl;

import com.scenomania.dao.AreaDao;
import com.scenomania.entities.Area;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	public Area getById(Integer id) {
		return (Area) this.sessionFactory.getCurrentSession().createQuery("from Area area where id = ?").setParameter(0, id).uniqueResult();
	}
}
