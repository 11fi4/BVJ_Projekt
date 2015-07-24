package com.impl.database.connection.access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Absent;
import com.impl.database.elements.Comment;
import com.impl.database.elements.Detension;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Student;
import com.impl.database.elements.UserAccount;
import com.impl.database.elements.Warning;

public class DBAccessImpl implements DBAccess {
	public DBAccessImpl() {
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();
		// create citeria to get student steinam33
		session = connectionManager.getSessionFactory().openSession();
	}

	private Session session;

	@Override
	public List<Student> getAllStudentsInClass(String className) {
		// first test implementation - not final
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class).add(
				Restrictions.eq("name", className));

		criteria.setMaxResults(10);
		List<Student> list = criteria.list();
		// Student studentGet = (Student) list.iterator().next();
		return list;
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

	// public List<Student> getClassByName(String className) {
	// Transaction tx = session.beginTransaction();
	//
	// Criteria criteria =
	// session.createCriteria(Student.class).add(Restriction.eq("name",
	// className));
	// }

	@Override
	public UserAccount getUser(String username, String password) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(UserAccount.class)
				.add(Restrictions.eq("Username", username))
				.add(Restrictions.eq("Password", password));

		UserAccount user = null;
		if (criteria.list().size() > 0) {
			user = (UserAccount) criteria.list().get(0);
		}

		return user;
	}

	@Override
	public List<Absent> getStudentAbsents(String studentId) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Absent.class).add(
				Restrictions.eq("student_id", studentId));

		List<Absent> list = new ArrayList<Absent>();
		criteria.list().forEach((instance) -> {
			list.add((Absent) instance);
		});
		return list;
	}

	@Override
	public List<Detension> getStudentDetensions(String studentId) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Detension.class).add(
				Restrictions.eq("student_id", studentId));

		List<Detension> list = new ArrayList<Detension>();
		criteria.list().forEach((instance) -> {
			list.add((Detension) instance);
		});
		return list;
	}

	@Override
	public List<Comment> getStudentComments(String studentId) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Comment.class).add(
				Restrictions.eq("student_id", studentId));

		List<Comment> list = new ArrayList<Comment>();
		criteria.list().forEach((instance) -> {
			list.add((Comment) instance);
		});
		return list;
	}

	@Override
	public List<Parent> getStudentParents(String studentId) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Parent.class).add(
				Restrictions.eq("student_id", studentId));

		List<Parent> list = new ArrayList<Parent>();
		criteria.list().forEach((instance) -> {
			list.add((Parent) instance);
		});
		return list;
	}

	@Override
	public List<Warning> getStudentWarnings(String studentId) {
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Warning.class).add(
				Restrictions.eq("student_id", studentId));

		List<Warning> list = new ArrayList<Warning>();
		criteria.list().forEach((instance) -> {
			list.add((Warning) instance);
		});
		return list;
	}

}
