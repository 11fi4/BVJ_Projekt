package com.impl.database.connection.access;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Student;

public class DBAccessImpl implements DBAccess {
	public DBAccessImpl() {
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();
		// create citeria to get student steinam33
		session = connectionManager.getSessionFactory().openSession();
	}

	private Session session;

	@Override
	public List<Student> getAllStudents(String className) {
		// first test implementation - not final
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class).add(
				Restrictions.eq("name", "Steinam_33"));

		criteria.setMaxResults(10);
		List<Student> list = criteria.list();
		Student studentGet = (Student) list.iterator().next();
		return null;
	}

	@Override
	public Student getStudentByName(String className, String studentName) {
		// first test implementation - not final 
		
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class)
				.add(Restrictions.eq("name", studentName))
				.add(Restrictions.eq("class", className));

		criteria.setMaxResults(1);
		Student student = (Student) criteria.list().get(0);
		
		return student;
	}
}
