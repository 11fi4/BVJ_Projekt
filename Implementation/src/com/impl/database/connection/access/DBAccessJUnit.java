package com.impl.database.connection.access;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.impl.database.elements.Student;
import com.impl.database.elements.User;
import com.impl.database.elements.Class;

public class DBAccessJUnit {
		// JUnit testing for DBAccessImpl
	public DBAccessJUnit() {
		accessImpl = new DBAccessImpl();
		// fill DB with data
		TestHelper.fillDB();
	}
	
	private DBAccess accessImpl;
	
	@Test
	public void getStudentSteinam() {
		Student expectedStudent = new Student();
		expectedStudent.setName("Steinam");
		expectedStudent.setAddress("Address");
		expectedStudent.setBirthdate(new Date(1960, 1, 1));
		
		assertEquals("Expected user = Steinam", expectedStudent, accessImpl.getStudentByName("11fi4", "Steinam"));
	}

	@Test
	public void getWrongStudent() {
		Student expectedStudent = new Student();
		expectedStudent.setName("Steinam");
		expectedStudent.setAddress("Address");
		expectedStudent.setBirthdate(new Date(1960, 1, 1));
		
		assertEquals("Expected = not Steinam", expectedStudent, accessImpl.getStudentByName("11fi4", "Wallner"));
	}
	
	@Test
	public void getTenStudents() {
		List<Student> expectedStudentsList = new ArrayList<Student>();
		Student expectedStudentInList = new Student();
		expectedStudentInList.setName("Steinam");
		expectedStudentInList.setAddress("Address");
		expectedStudentInList.setBirthdate(new Date(1960, 1, 1));
		
		for (int i = 0; i < 10; i++) {
			expectedStudentsList.add(expectedStudentInList);
		}
		
		assertEquals("Expected = 10 Students in list", expectedStudentsList, accessImpl.getAllStudents("11fi4"));
	}
	
	@Test
	public void getZeroStudents() {
		List<Student> expectedStudentsList = new ArrayList<Student>();
				
		assertEquals("Expected = 0 Students in list", expectedStudentsList, accessImpl.getAllStudents("0fi4"));
	}
	
	@Test
	public void getCorrectUser() {
		User expectedUser = new User();
		expectedUser.setName("Steinam");
		Set<Class> classSet = new LinkedHashSet<Class>();
		expectedUser.setClasses(classSet);
		
		assertEquals("Expected user = Steinam", expectedUser, accessImpl.getUser("steinam", "12345678"));
	}
}
