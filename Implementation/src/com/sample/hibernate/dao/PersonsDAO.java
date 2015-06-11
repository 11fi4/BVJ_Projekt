/**
 * 
 */
package com.sample.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sample.hibernate.dto.Persons;

/**
 * @author Vienna
 * 
 */

@SuppressWarnings("deprecation")
public class PersonsDAO {
	private static SessionFactory factory;

	public PersonsDAO() {
		getSession();
	}

	public void getSession() {
		try {
			factory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Persons get(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Persons persons = null;
		try {
			tx = session.beginTransaction();
			persons = (Persons) session.get(Persons.class, id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persons;
	}

	public void update(Persons persons) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(persons);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void insert(Persons persons) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(persons);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
