/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.dao.impl;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUserDao implements UserDao {

	@Autowired(required=true)
	private SessionFactory sessionFactory;

	public User findById(Integer id) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from User users where users.id = ?").setParameter(0, id);
		User user = (User) q.uniqueResult();
		return user;
	}

	public User persistOrMerge(User user) {
		return (User) this.sessionFactory.getCurrentSession().merge(user);
	}

}