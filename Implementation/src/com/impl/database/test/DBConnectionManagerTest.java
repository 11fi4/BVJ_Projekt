package com.impl.database.test;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Student;

public class DBConnectionManagerTest {

	private static DBConnectionManagerImpl connectionManager;

	@BeforeClass
	public static void initConnectionManager() {
		connectionManager = new DBConnectionManagerImpl();
		dropAllTables();
	}

	private static void dropAllTables() {
		connectionManager.createHQLQuery("DELETE FROM Absent");
		connectionManager.createHQLQuery("DELETE FROM Class");
		connectionManager.createHQLQuery("DELETE FROM ClassUser");
		connectionManager.createHQLQuery("DELETE FROM Comment");
		connectionManager.createHQLQuery("DELETE FROM Detension");
		connectionManager.createHQLQuery("DELETE FROM Parent");
		connectionManager.createHQLQuery("DELETE FROM Permission");
		connectionManager.createHQLQuery("DELETE FROM Role");
		connectionManager.createHQLQuery("DELETE FROM RoleDescription");
		connectionManager.createHQLQuery("DELETE FROM Student");
		connectionManager.createHQLQuery("DELETE FROM UserAccount");
		connectionManager.createHQLQuery("DELETE FROM Warning");

	}

	@AfterClass
	public static void afterTests() {
		dropAllTables();
	}

	@Test
	public void insertAndCheckoutStudentTest() {
		// Insert test record
		Student student = new Student();
		student.setFirstName("Karl");
		student.setLastName("Steinam");
		student.setBirthdate(new Date(2015, 6, 15));
		student.setGender("männlich");
		student.seteMail("steinam@steinam.de");
		student.setPhoneNumber("0049931294637");
		student.setAddress("strasse 6, 97083 Würzburg");
		connectionManager.insert(student);
		Integer studentId = student.getStudent_id();

		// Checkout the existing record
		Student student2 = (Student) connectionManager.get(Student.class,
				studentId);

	}
}
