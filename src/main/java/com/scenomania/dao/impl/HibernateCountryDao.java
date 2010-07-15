package com.scenomania.dao.impl;

import com.scenomania.dao.CountryDao;
import com.scenomania.entities.Country;
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

	public Country getByCode(String code) {
		return (Country) this.sessionFactory.getCurrentSession().createQuery("from Country countries where code = ?").setParameter(0, code).uniqueResult();
	}
}
