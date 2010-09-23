package com.scenomania.dao.impl;

import com.scenomania.dao.PromoterDao;
import com.scenomania.entities.City;
import com.scenomania.entities.Promoter;
import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eugene
 */
@Repository
public class HibernatePromoterDao implements PromoterDao {

	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public Promoter find(String name, Locale locale, City city) {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from Promoter where name = ? and homecity_id = ?"
				)
				.setParameter(0, name)
				.setParameter(1, city.getId()
				);

		return (Promoter) q.uniqueResult();
	}

	public Promoter save(Promoter promoter) {
		return (Promoter) this.sessionFactory.getCurrentSession().merge(promoter);
	}

	public List<Promoter> fetchAll() {
		return (List<Promoter>) sessionFactory.getCurrentSession().createQuery("from Promoter").list();
	}
}
