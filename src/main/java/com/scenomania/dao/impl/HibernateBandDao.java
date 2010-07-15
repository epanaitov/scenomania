package com.scenomania.dao.impl;

import com.scenomania.dao.BandDao;
import com.scenomania.entities.Band;
import org.hibernate.SessionFactory;
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
}
