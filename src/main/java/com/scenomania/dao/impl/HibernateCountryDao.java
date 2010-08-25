package com.scenomania.dao.impl;

import com.scenomania.dao.CountryDao;
import com.scenomania.entities.Country;
import com.scenomania.entities.CountryLocale;
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
//public class HibernateCountryDao extends HibernateDaoBase<Country> implements CountryDao {
public class HibernateCountryDao implements CountryDao {
	
	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public Country findById(Integer id) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from Country" + "  where id = ?").setParameter(0, id);
		Country row = (Country) q.uniqueResult();
		return row;
	}

	public Country persistOrMerge(Country obj) {
		return (Country) this.sessionFactory.getCurrentSession().merge(obj);
	}

	public List<Country> fetchAll() {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from Country");
		return q.list();
	}
	
	@Transactional
	public List<Country> fetchAll(String locale){
		/*
		Query q = this.sessionFactory.getCurrentSession().createQuery(
				 " from Country as c"
				+ " left join fetch c.locales as l"
				+ " where l.locale = ?"
				)
				.setParameter(0, locale)
				;
		return (List<Country>)q.list();
		*/

		Query q = this.sessionFactory.getCurrentSession().createSQLQuery(
				"SELECT "
				+ "IFNULL(cl.name, c.name) as name, c.* FROM countries c "
				+ "LEFT JOIN country_locale cl ON (c.id  = cl.country_id) AND (cl.locale = ?) ORDER BY name"
				).addEntity(Country.class).setString(0, locale);

		return (List<Country>) q.list();
	}
	
	public Country getByCode(String code) {
		return (Country) this.sessionFactory.getCurrentSession().createQuery("from Country countries where code = ?").setParameter(0, code).uniqueResult();
	}
	

}
