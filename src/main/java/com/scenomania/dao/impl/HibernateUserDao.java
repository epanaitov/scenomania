package com.scenomania.dao.impl;

import com.scenomania.dao.UserDao;
import com.scenomania.entities.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
// since spring 3.1
//public class HibernateUserDao extends HibernateDaoBase<User> implements UserDao {
public class HibernateUserDao implements UserDao {

	@Autowired(required=true)
	protected SessionFactory sessionFactory;

	public User findById(Integer id) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from User" + "  where id = ?").setParameter(0, id);
		User row = (User) q.uniqueResult();
		return row;
	}

	public User persistOrMerge(User obj) {
		return (User) this.sessionFactory.getCurrentSession().merge(obj);
	}

	public User findByEmail(String email) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from User users where users.email = ?").setParameter(0, email);
		User user = (User) q.uniqueResult();
		return user;
	}

	public User refresh(User user) {
		sessionFactory.getCurrentSession().refresh(user);
		return user;
	}
}