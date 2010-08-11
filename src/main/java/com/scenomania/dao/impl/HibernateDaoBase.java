package com.scenomania.dao.impl;

import com.scenomania.dao.DaoBase;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eugene
 */

@Repository
public class HibernateDaoBase<E> implements DaoBase<E> {

	private E entity;

	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public E findById(Integer id) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from " + entity.getClass().getName() + "  where id = ?").setParameter(0, id);
		E row = (E) q.uniqueResult();
		return row;
	}

	public E persistOrMerge(E obj) {
		return (E) this.sessionFactory.getCurrentSession().merge(obj);
	}
}
