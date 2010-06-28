package com.scenomania.dao.impl;

import com.scenomania.dao.CityDao;
import com.scenomania.entities.City;
import java.util.ArrayList;
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
public class HibernateCityDao implements CityDao {

	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public City persistOrMerge(City obj) {
		return (City) this.sessionFactory.getCurrentSession().merge(obj);
	}

	public List<City> fetchByCountryCode(String code) {
		List<City> cities = new ArrayList<City>();

		Query q = this.sessionFactory.getCurrentSession().createQuery("from City cities" + "  where country_code = ?").setParameter(0, code);
		
		cities = q.list();
		return cities;
	}

	public City getById(Integer id) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from City" + "  where id = ?").setParameter(0, id);
		City row = (City) q.uniqueResult();
		return row;
	}
}
