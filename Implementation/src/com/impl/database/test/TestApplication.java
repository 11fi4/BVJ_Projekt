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
import com.impl.database.elements.UserAccount;

/**
 * @author kuslu
 *
 */
public class TestApplication extends DBConnectionManagerImpl {

	public static void main(String args[]) throws Exception {
		Set<Parent> parents = new HashSet<Parent>();
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();

		 // Insert new record
		 Student student1 = new Student();
		 student1.setFirstName("Karl");
		 student1.setLastName("Steinam");
		 student1.setBirthdate(new Date(2015, 6, 15));
		 student1.setGender("m�nnlich");
		 student1.seteMail("steinam@steinam.de");
		 student1.setPhoneNumber("0049931294637");
		 student1.setAddress("Salamancastrasse 6, 97084 W�rzburg");
		 connectionManager.insert(student1);
		 Integer studentId = student1.getStudent_id();
		
		 // Get existing record
		 Student student2 = (Student) connectionManager.get(Student.class,
		 studentId);
		 if (null != student2) {
		 System.out.println(student2.getFirstName());
		 System.out.println(student2.getLastName());
		 System.out.println(student2.getGender());
		 System.out.println(student2.geteMail());
		 } else {
		 System.out.println("No records found");
		 }
		
		 // Update existing record
		if(student2!=null){
			 student2.setFirstName("Karl");
		 student2.setLastName("Steinam_2");
		 student2.setBirthdate(new Date(2015, 6, 15));
		 student2.setGender("weiblich");
		 student2.seteMail("steinam2@steinam.de");
		 student2.setPhoneNumber("0049931294637");
		 student2.setAddress("Salamancastrasse 6, 97084 W�rzburg");
		 connectionManager.update(student2);
		}else{
			throw new Exception("Student is null");
		}
		 // Get existing record
		
		 Student student3 = (Student) connectionManager.get(Student.class,
		 studentId);
		 if (null != student3) {
		 System.out.println(student3.getFirstName());
		 System.out.println(student3.getLastName());
		 System.out.println(student3.getGender());
		 System.out.println(student3.geteMail());
		
		
		 // Add parent to the student
		
		 Parent parent = new Parent();
		 parent.setFirstName("Casandra");
		 parent.setLastName("Wallner");
		 parent.setPhoneNumber("04993103847");
		 parent.setAddress("Ohmstrasse 5, 97083 W�rzburg");
		 parent.seteMail("wallner@gmail.com");
		 parents.add(parent);
		 student3.setParents(parents);
		 connectionManager.update(student3);
		 } else {
		 System.out.println("No records found");
		 }
		 // delete student
		
		 connectionManager.delete(student3);
		
		 // add more records into student table
		parents.removeAll(parents);
		
		 for (int i = 1; i < 50; i++) {
		 Student student4 = new Student();
		 student4.setFirstName("Karl");
		 student4.setLastName("Steinam_" + i);
		 student4.setBirthdate(new Date(1920 + i, 1, 1));
		 student4.setGender(((i % 2) != 0) ? "m�nnlich" : "weiblich");
		 student4.seteMail("steinam_" + i + "@steinam.de");
		 student4.setPhoneNumber("0049931294637");
		 student4.setAddress("Salamancastrasse " + i + " , 97084 W�rzburg");
		
		 // add few parent
		 Parent parent1 = new Parent();
		 parent1.setFirstName("Casandra" );
		 parent1.setLastName("Wallner" + i);
		 parent1.setPhoneNumber("04993103847");
		 parent1.setAddress("Ohmstrasse 5, 97083 W�rzburg");
		 parent1.seteMail("wallner@gmail.com");
		 parents.add(parent1);
		
		 Parent parent2 = new Parent();
		 parent2.setFirstName("Tommy");
		 parent2.setLastName("Zobel" + i);
		 parent2.setPhoneNumber("04993103647");
		 parent2.setAddress("Ohmstrasse 6, 97083 W�rzburg");
		 parent2.seteMail("zobel@gmail.com");
		 parents.add(parent2);
		
		 student4.setParents(parents);
		 connectionManager.insert(student4);
		 parents.removeAll(parents);
		 }

		 UserAccount user = new UserAccount();
		 user.setName("user1");
		 user.setUsername("bla");
		 user.setPassword("pass");
		 connectionManager.insert(user);
		 
//		// create citeria to get student steinam33
//		Session session = connectionManager.getSessionFactory().openSession();
//
//		// Transaction tx = session.beginTransaction();
//
//		Criteria criteria = session.createCriteria(Student.class).add(
//				Restrictions.eq("lastName", "Steinam_33"));
//		criteria.setMaxResults(10);
//		List<?> list = criteria.list();
//		Student studentGet = (Student) list.iterator().next();
//		System.out.println(studentGet.getFirstName());
//		session.close();
//		// Exit
//		Thread.currentThread().interrupt();
	}
}
