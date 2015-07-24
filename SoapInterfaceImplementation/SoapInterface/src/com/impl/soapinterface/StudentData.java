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
	}
	
	
	/*
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "user_id", unique = true, nullable = false)
	protected int user_id;

	@Column(name = "name", nullable = false)
	protected String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Class> classes = new HashSet<Class>();

	public User() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}
	*/
}
