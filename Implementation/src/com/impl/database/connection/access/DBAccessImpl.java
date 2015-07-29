package com.impl.database.connection.access;

import java.util.ArrayList;
import java.util.Date;
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
	private DBConnectionManager dbConnectionManager = null;

	// private Session session;

	public DBAccessImpl() {
		dbConnectionManager = new DBConnectionManagerImpl();
		// create citeria to get student steinam33
		// session = dbConnectionManager.getSessionFactory().openSession();
	}

	@Override
	public List<Student> requestAllStudentsInClass(String className) {
		Session session = dbConnectionManager.getSessionFactory().openSession();
		// first test implementation - not final
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class).add(
				Restrictions.eq("name", className));

		criteria.setMaxResults(10);
		List<Student> list = criteria.list();

		tx.commit();

		session.close();

		// Student studentGet = (Student) list.iterator().next();
		return list;
	}

	@Override
	public List<Student> requestStudentByName(String firstName, String lastName) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class)
				.add(Restrictions.eq("firstName", firstName))
				.add(Restrictions.eq("lastName", lastName));

		List<Student> list = new ArrayList<Student>();
		criteria.list().forEach((instance) -> {
			list.add((Student) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public List<Student> requestStudentByName(String firstName,
			String lastName, String classId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Student.class)
				.add(Restrictions.eq("firstName", firstName))
				.add(Restrictions.eq("lastName", lastName));

		// todo: add filter for class

		List<Student> list = new ArrayList<Student>();
		criteria.list().forEach((instance) -> {
			list.add((Student) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	// public List<Student> getClassByName(String className) {
	// Transaction tx = session.beginTransaction();
	//
	// Criteria criteria =
	// session.createCriteria(Student.class).add(Restriction.eq("name",
	// className));
	// }

	@Override
	public UserAccount requestUser(String username, String password) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(UserAccount.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password));

		UserAccount user = null;
		if (criteria.list() != null && criteria.list().size() > 0) {
			user = (UserAccount) criteria.list().get(0);
		}

		tx.commit();

		session.close();

		return user;
	}

	@Override
	public List<Absent> requestStudentAbsents(String studentId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Absent.class).add(
				Restrictions.eq("student_id", studentId));

		List<Absent> list = new ArrayList<Absent>();
		criteria.list().forEach((instance) -> {
			list.add((Absent) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public List<Detension> requestStudentDetensions(String studentId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Detension.class).add(
				Restrictions.eq("student_id", studentId));

		List<Detension> list = new ArrayList<Detension>();
		criteria.list().forEach((instance) -> {
			list.add((Detension) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public List<Comment> requestStudentComments(String studentId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Comment.class).add(
				Restrictions.eq("student_id", studentId));

		List<Comment> list = new ArrayList<Comment>();
		criteria.list().forEach((instance) -> {
			list.add((Comment) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public List<Parent> requestStudentParents(String studentId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Parent.class).add(
				Restrictions.eq("student_id", studentId));

		List<Parent> list = new ArrayList<Parent>();
		criteria.list().forEach((instance) -> {
			list.add((Parent) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public List<Warning> requestStudentWarnings(String studentId) {
		Session session = dbConnectionManager.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Warning.class).add(
				Restrictions.eq("student_id", studentId));

		List<Warning> list = new ArrayList<Warning>();
		criteria.list().forEach((instance) -> {
			list.add((Warning) instance);
		});

		tx.commit();

		session.close();

		return list;
	}

	@Override
	public void insertUser(String firstName, String lastName, String username,
			String password) {

		UserAccount userAcc = new UserAccount();
		userAcc.setName(firstName + " " + lastName);
		userAcc.setPassword(password);
		userAcc.setUsername(username);

		dbConnectionManager.insert(userAcc);
	}

	@Override
	public void insertStudent(String firstName, String lastName,
			Date birthdate, String phone, String eMail, String address,
			String gender) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setBirthdate(birthdate);
		student.setPhoneNumber(phone);
		student.seteMail(eMail);
		student.setAddress(address);
		student.setGender(gender);

		dbConnectionManager.insert(student);
	}

	@Override
	public void addUserRole(String userId, String roleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String username, String password) {
		UserAccount userToDelete = requestUser(username, password);
		if (userToDelete != null) {
			dbConnectionManager.delete(userToDelete);
		}
	}

}
