package com.impl.soapinterface;

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
}
