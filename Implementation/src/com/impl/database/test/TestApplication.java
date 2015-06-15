/**
 * 
 */
package com.impl.database.test;

import java.sql.Timestamp;
import java.util.Date;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Student;

/**
 * @author kuslu
 *
 */
public class TestApplication extends DBConnectionManagerImpl {

	public static void main(String args[]) {
		
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();

		// Insert new record
		Student student1 = new Student();
		student1.setName("Steinam");
		student1.setBirthdate(new Date(2015, 6, 15));
		student1.setGender("männlich");
		student1.setEMail("steinam@steinam.de");
		student1.setPhoneNumber("0049931294637");
		student1.setAddress("Salamancastrasse 6, 97084 Würzburg");
		connectionManager.insert(student1);
		Integer studentId = student1.getStudent_id();

		// Get existing record
		Student student = (Student) connectionManager.get(Student.class,
				studentId);
		if (null != student) {
			System.out.println(student.toString());
		} else {
			System.out.println("No records found");
		}

		// Update existing record
		Student student2 = new Student();
		student2.setName("Steinam_2");
		student2.setBirthdate(new Date(2015, 6, 15));
		student2.setGender("weiblich");
		student2.setEMail("steinam2@steinam.de");
		student2.setPhoneNumber("0049931294637");
		student2.setAddress("Salamancastrasse 6, 97084 Würzburg");
		connectionManager.update(student2);

		// Get existing record
		Student student3 = (Student) connectionManager.get(Student.class,
				studentId);
		if (null != student3) {
			System.out.println(student2.toString());
		} else {
			System.out.println("No records found");
		}
	}
}
