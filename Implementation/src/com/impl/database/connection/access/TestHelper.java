package com.impl.database.connection.access;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Student;

public class TestHelper {

	// Test main (for DBAccessImpl)
	public static void main(String[] args) {
		DBAccessImpl impl = new DBAccessImpl();

		fillDB();
		
		Student student = impl.getStudentByName("11fi4", "steinam");

		System.out.print(student.getName());
	}

	public synchronized static void fillDB() {
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();
		
		Set<Parent> parents = new HashSet<Parent>();
		Parent parent = new Parent();
		parent.setName("Wllner");
		parent.setPhoneNumber("04993103847");
		parent.setAddress("Ohmstrasse 5, 97083 Würzburg");
		parents.add(parent);

		for (int i = 0; i < 100; i++) {
			Student student4 = new Student();
			student4.setName("Steinam_" + i);
			student4.setBirthdate(new Date(1920 + i, 1, 1));
			student4.setGender(((i % 2) != 0) ? "männlich" : "weiblich");
			student4.setEMail("steinam_" + i + "@steinam.de");
			student4.setPhoneNumber("0049931294637");
			student4.setAddress("Salamancastrasse " + i + " , 97084 Würzburg");

			// add few parent
			Parent parent1 = new Parent();
			parent1.setName("Wllner" + i);
			parent1.setPhoneNumber("04993103847");
			parent1.setAddress("Ohmstrasse 5, 97083 Würzburg");
			parents.add(parent1);

			Parent parent2 = new Parent();
			parent2.setName("zobel" + i);
			parent2.setPhoneNumber("04993103647");
			parent2.setAddress("Ohmstrasse 6, 97083 Würzburg");
			parents.add(parent2);

			student4.setParents(parents);
			connectionManager.insert(student4);
			parents.removeAll(parents);
		}
	}
}
