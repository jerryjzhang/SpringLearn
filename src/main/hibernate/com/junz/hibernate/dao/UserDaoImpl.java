package com.junz.hibernate.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.junz.hibernate.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	@Resource
	private SessionFactory sessionFactory;
	
	public User get(int id){
		System.out.println("Junz session: " + this.sessionFactory.getCurrentSession().hashCode());
		return (User)this.sessionFactory.getCurrentSession().get(User.class, id);
		/*
		LogInterceptor l = new LogInterceptor();
		System.out.println("Junz interceptor: " + l.hashCode());
		Session session = this.sessionFactory.openSession(l);
		System.out.println("Junz session: " + session.hashCode());
		return (User)session.get(User.class, id);
		*/
	}
	
	public void save(User user){
		System.out.println("Junz session: " + this.sessionFactory.getCurrentSession().hashCode());
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		/*
		LogInterceptor l = new LogInterceptor();
		System.out.println("Junz interceptor: " + l.hashCode());
		Session session = this.sessionFactory.openSession(l);
		System.out.println("Junz session: " + session.hashCode());
		session.saveOrUpdate(user);
		*/
	}
}
