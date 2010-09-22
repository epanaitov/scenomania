package com.scenomania.dao.impl;

import com.scenomania.dao.BandDao;
import com.scenomania.entities.Band;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eugene
 */

@Repository
public class HibernateBandDao implements BandDao {

	@Autowired
	protected SessionFactory sessionFactory;

	public Band findById(Integer id) {
		return (Band) this.sessionFactory.getCurrentSession().get(Band.class, id);
	}

	public Band save(Band band) {
		return (Band) this.sessionFactory.getCurrentSession().merge(band);
	}

	public List<Band> suggest(HashMap params) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Band.class);

		Iterator<String> itr = params.keySet().iterator();
		while (itr.hasNext()) {
			String field = itr.next();
			if (field.equals("query")) criteria.add(Restrictions.like("name", "%"+params.get(field)+"%"));
			else criteria.add(Restrictions.eq(field, params.get(field)));
		}
		
		return (List<Band>) criteria.list();
	}

	public Band getWhere(HashMap params) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Band.class);

		Iterator<String> itr = params.keySet().iterator();
		while (itr.hasNext()) {
			String field = itr.next();
			criteria.add(Restrictions.eq(field, params.get(field)));
		}

		return (Band) criteria.uniqueResult();
	}

	public List<Band> fetchAll() {
		return sessionFactory.getCurrentSession().createQuery("from Band").list();
	}
}
