package com.impl.database.connection.access;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.impl.database.elements.Student;

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
}
