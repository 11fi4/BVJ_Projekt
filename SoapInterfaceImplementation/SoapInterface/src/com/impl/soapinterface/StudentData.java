package com.impl.soapinterface;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.impl.database.elements.Class;
import com.impl.database.elements.Student;

public class StudentData {
	public static String getStudentFirstName(int studentID){
		//Todo DB
		return "DummyStudentName";
	}
	
	public static String getStudentLastName(int studentID){
		//Todo DB
		return "DummyStudentName";
	}
	
	public static Integer getStudentClass(int studentID){
		//Todo DB
		return 1337;
	}
	
	public static String getStudentEmail(int studentID){
		//Todo DB
		return "1337";
	}
	
	public static String getStudentPhone(int studentID){
		//Todo DB
		return "1337";
	}
	
	public static String getStudentAddress(int studentID){
		//Todo DB
		return "1337";
	}
	
	public static Gender getStudentGender(int studentID){
		//Todo DB
		return Gender.Steinam;
	}
	
	public static void addStudent(
			String firstName, 
			String lastName, 
			Date birthdate, 
			//int contactId, 
			int classId, 
			String email, 
			String phoneNumber, 
			String address,
			String gender
		){
		
		//Todo DB
		Student newStudent = new Student();
		newStudent.setBirthdate(birthdate);
		newStudent.setPhoneNumber(phoneNumber);
		newStudent.setGender(gender);
		//Todo firstname
		//newStudent.setName(name);
		
		System.out.println("Adding User to DB: "+firstName);
		SoapConnectionManager.dbAccess.insertStudent(firstName, lastName, birthdate, phoneNumber, email, address, gender);
	}
	
	public static void addStudentAbsence(int studentId, Date startTime, Date endTime, boolean medicalCert, boolean excused, boolean parentsContacted){
		//TODO DB Implement
	}
	
	public static void deleteStudent(int studentId){
		//TODO DB Implement
		//SoapConnectionManager.dbAccess.deleteStudent(studentId);
	}
}
