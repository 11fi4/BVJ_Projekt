/**
 * 
 */
package com.impl.database.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.tree.RestrictableStatement;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Student;

/**
 * @author kuslu
 *
 */
public class TestApplication extends DBConnectionManagerImpl {

	public static void main(String args[]) {
		Set<Parent> parents = new HashSet<Parent>();
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();

		// Insert new record
		Student student1 = new Student();
		student1.setName("Steinam");
		student1.setBirthdate(new Date(2015, 6, 15));
		student1.setGender("m�nnlich");
		student1.setEMail("steinam@steinam.de");
		student1.setPhoneNumber("0049931294637");
		student1.setAddress("Salamancastrasse 6, 97084 W�rzburg");
		connectionManager.insert(student1);
		Integer studentId = student1.getStudent_id();

		// Get existing record
		Student student2 = (Student) connectionManager.get(Student.class,
				studentId);
		if (null != student2) {
			System.out.println(student2.getName());
			System.out.println(student2.getGender());
			System.out.println(student2.getEMail());
		} else {
			System.out.println("No records found");
		}

		// Update existing record

		student2.setName("Steinam_2");
		student2.setBirthdate(new Date(2015, 6, 15));
		student2.setGender("weiblich");
		student2.setEMail("steinam2@steinam.de");
		student2.setPhoneNumber("0049931294637");
		student2.setAddress("Salamancastrasse 6, 97084 W�rzburg");
		connectionManager.update(student2);

		// Get existing record
		Student student3 = (Student) connectionManager.get(Student.class,
				studentId);
		if (null != student3) {
			System.out.println(student3.getName());
			System.out.println(student3.getGender());
			System.out.println(student3.getEMail());
		} else {
			System.out.println("No records found");
		}

		// Add parent to the student

		Parent parent = new Parent();
		parent.setName("Wllner");
		parent.setPhoneNumber("04993103847");
		parent.setAddress("Ohmstrasse 5, 97083 W�rzburg");
		parents.add(parent);
		student3.setParents(parents);
		connectionManager.update(student3);

		// delete student

		connectionManager.delete(student3);

		// add more records into student table

		for (int i = 1; i < 50; i++) {
			Student student4 = new Student();
			student4.setName("Steinam_" + i);
			student4.setBirthdate(new Date(1920 + i, 1, 1));
			student4.setGender(((i % 2) != 0) ? "m�nnlich" : "weiblich");
			student4.setEMail("steinam_" + i + "@steinam.de");
			student4.setPhoneNumber("0049931294637");
			student4.setAddress("Salamancastrasse " + i + " , 97084 W�rzburg");

			// add few parent
			Parent parent1 = new Parent();
			parent1.setName("Wllner" + i);
			parent1.setPhoneNumber("04993103847");
			parent1.setAddress("Ohmstrasse 5, 97083 W�rzburg");
			parents.add(parent1);

			Parent parent2 = new Parent();
			parent2.setName("zobel" + i);
			parent2.setPhoneNumber("04993103647");
			parent2.setAddress("Ohmstrasse 6, 97083 W�rzburg");
			parents.add(parent2);

			student4.setParents(parents);
			connectionManager.insert(student4);
			parents.removeAll(parents);
		}

		// create citeria to get student steinam33
		Session session = connectionManager.getSessionFactory().openSession();
				
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Student.class).add(
				Restrictions.eq("name", "Steinam_33"));
		criteria.setMaxResults(10);
		List list = criteria.list();
		Student studentGet = (Student) list.iterator().next();
System.out.println(studentGet.getName());
		// Exit
		Thread.currentThread().interrupt();
	}
}
