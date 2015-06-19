/**
 * 
 */
package com.impl.database.connection;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kuslu
 *
 */
@SuppressWarnings("deprecation")
public class DBConnectionManagerImpl implements DBConnectionManager {
	private static SessionFactory factory;
	static final Logger logger = LoggerFactory
			.getLogger(DBConnectionManagerImpl.class);

	public DBConnectionManagerImpl() {
		getSessionFactory();
	}

	/*
	 * this method create Objects in the database
	 */
	@Override
	public void insert(Object object) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/*
	 * this method delete Objects in the database
	 */
	@Override
	public void delete(Object object) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/*
	 * this method update Objects in the database
	 */
	@Override
	public void update(Object object) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(object);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	/*
	 * this method return a called object
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object get(Class clazz, Object object) {
		Session session = factory.openSession();
		Transaction tx = null;
		Object obj = null;
		try {
			tx = session.beginTransaction();
			obj = session.get(clazz, (Serializable) object);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return obj;

	}

	@Override
	public SessionFactory getSessionFactory() {
		try {
			factory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Exception ex) {
			logger.error("Unexpected connection error" + ex.getCause());
			ex.printStackTrace();
		}
		return factory;
	}

}
