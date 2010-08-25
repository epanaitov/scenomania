package com.scenomania.dao.impl;

import com.scenomania.dao.CityDao;
import com.scenomania.entities.City;
import com.scenomania.entities.Country;

import java.util.ArrayList;
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

	public List<City> fetchByCodes(String country, String area) {
		List<City> cities = new ArrayList<City>();

		Query q = this.sessionFactory.getCurrentSession().createQuery("from City cities" + "  where country_code = ? and area_code = ?").setParameter(0, country).setParameter(1, area);

		cities = q.list();
		return cities;
	}

	public List<City> fetchAll() {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from City where area_id is null");
		q.setMaxResults(250000);
		return (List<City>) q.list();
	}
	
	@Transactional
	public List<City> fetchAll(String locale){
		Query q = this.sessionFactory.getCurrentSession().createQuery(
				 " from City as c"
				+ " left join fetch c.locales as l"
				+ " where l.locale = ?"
				)
				.setParameter(0, locale)
				;
		return (List<City>)q.list();
	}

	public List<City> fetchByArea(Integer areaId, String locale) {
		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT "
				+ "IFNULL(cl.name, c.name) as name, IFNULL(cl.description, c.description) as description, c.* FROM cities c "
				+ "LEFT JOIN city_locale cl ON (c.id  = cl.city_id) AND (cl.locale = ?) "
				+ "WHERE (c.area_id = ?) AND (c.population > 0) ORDER BY population DESC"
				).addEntity(City.class)
				.setString(0, locale)
				.setInteger(1, areaId);

		return (List<City>) q.list();
	}
}
