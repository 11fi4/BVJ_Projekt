package com.impl.database.connection.access;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.impl.database.connection.DBConnectionManager;
import com.impl.database.connection.DBConnectionManagerImpl;
import com.impl.database.elements.ClassUser;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Permission;
import com.impl.database.elements.Role;
import com.impl.database.elements.Student;
import com.impl.database.elements.Class;
import com.impl.database.elements.UserAccount;

public class TestHelper {

	// Test main (for DBAccessImpl)
	public static void main(String[] args) {
		DBAccessImpl impl = new DBAccessImpl();

		fillDB();
		
		UserAccount userAccount = impl.getUser("username", "12345678");
		
		System.out.println(userAccount.getName());
	}

	public synchronized static void fillDB() {
		DBConnectionManager connectionManager = new DBConnectionManagerImpl();
		
		Student student1 = new Student();
		student1.setFirstName("Karl");
		student1.setLastName("Steinam");
		student1.setBirthdate(new Date(2015, 6, 15));
		student1.setGender("männlich");
		student1.seteMail("steinam@steinam.de");
		student1.setPhoneNumber("0049931294637");
		student1.setAddress("Salamancastrasse 6, 97084 Würzburg");
		connectionManager.insert(student1);
		
		// parents
		Set<Parent> parents = new HashSet<Parent>();
		Parent parent = new Parent();
		parent.setFirstName("Firstname");
		parent.setLastName("Lastname");
		parent.setPhoneNumber("04993103847");
		parent.setAddress("Ohmstrasse 5, 97083 Würzburg");
		parents.add(parent);	
		
		Set<Student> students = new HashSet<Student>();
//
//		// add students for list / class
		for (int i = 0; i < 100; i++) {
			Student student = new Student();
			student.setFirstName("Karl_" + i);
			student.setLastName("Steinam_" + i);
			student.setBirthdate(new Date(1920 + i, 1, 1));
			student.setGender(((i % 2) != 0) ? "männlich" : "weiblich");
			student.seteMail("steinam_" + i + "@steinam.de");
			student.setPhoneNumber("0049931294637");
			student.setAddress("Salamancastrasse " + i + " , 97084 Würzburg");

			// add few parent
			Parent parent1 = new Parent();
			parent1.setFirstName("Tina_" + i);
			parent1.setLastName("Wallner_" + i);
			parent1.setPhoneNumber("04993103847");
			parent1.setAddress("Ohmstrasse 5, 97083 Würzburg");
			parents.add(parent1);

			Parent parent2 = new Parent();
			parent2.setFirstName("Torsten_" + i);
			parent2.setLastName("Zobel_" + i);
			parent2.setPhoneNumber("04993103647");
			parent2.setAddress("Ohmstrasse 6, 97083 Würzburg");
			parents.add(parent2);

			student.setParents(parents);
			
			connectionManager.insert(student);
			parents.removeAll(parents);
			
			students.add(student);
		}
		
		// studentClasses
		Set<Class> studentClasses = new HashSet<Class>();
		Class studentClass = new Class();
		studentClass.setName("11fi4");
		studentClasses.add(studentClass);
		
		// role
		Role role = new Role();
		
		// Classusers
		Set<ClassUser> classUsersSet = new HashSet<ClassUser>();
		ClassUser classUser = new ClassUser();
		classUser.set_class(studentClass);
		classUser.setRole(role);
		
		role.setClassUser(classUser);
		
		Permission permission = new Permission();
		permission.setAdd("True");
		permission.setDelete("True");
		permission.setAdmin("True");
		permission.setEdit("True");
		permission.setRole(role);
		
		role.setPermission(permission);
		
		UserAccount steinamUser = new UserAccount();
		steinamUser.setName("SteinamUser");
		steinamUser.setPassword("12345678");
		steinamUser.setUsername("username");
		steinamUser.setClassUsers(classUsersSet);
		connectionManager.insert(steinamUser);
	}
}
