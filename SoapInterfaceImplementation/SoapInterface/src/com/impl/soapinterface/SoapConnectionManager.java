package com.impl.soapinterface;
import java.util.Date;
import java.util.HashMap;

import com.impl.soapinterface.responses.ResponseBool;
import com.impl.soapinterface.responses.ResponseDictionary;
import com.impl.soapinterface.responses.ResponseInt;
import com.impl.soapinterface.responses.ResponseLogin;
import com.impl.soapinterface.responses.ResponseString;
import com.impl.soapinterface.responses.ResponseStringArray;
import com.impl.soapinterface.responses.ResponseStringStringMap;
import com.impl.soapinterface.responses.ResponseBase;
import com.impl.soapinterface.responses.ResponseBase.ERROR_CODES;

public class SoapConnectionManager {

	private static UserAuthentification userAuth;

	static boolean isInitialized = false;

	// Dummy return
		// This class represents the implementation of any method call.
		// Please refer to this structure when implementing new methods
		public ResponseInt return5(String authMD5) {
			ResponseInt resp = new ResponseInt();

			if (userAuth.checkAthentification(authMD5, 0, resp)) {
				resp.setValue(5);
			}

			return resp;
		}
	
	public SoapConnectionManager() {
		if (!isInitialized) {
			System.out.println("Initialising new Server session");
			userAuth = new UserAuthentification();
			isInitialized = true;
		}
	}

	

	public ResponseBool logOut(String authMD5) {
		ResponseBool resp = new ResponseBool();

		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			resp.setValue(userAuth.logOutUser(authMD5));
		}
		return resp;
	}

	public ResponseLogin logIn(String username, String password) {
		ResponseLogin resp;

		System.out.println("Login Attempt, user / pass: " + username + ", "
				+ password);

		if (checkIfUserExists(username, password)) {
			// User is present in DB

			// Generate md5 with user, password and current time in ms
			String md5 = Utilities.getMD5(password + username
					+ String.valueOf(new Date().getTime()));

			if (userAuth.authenticateUser(username, md5)) {
				System.out.println("Login Successfull, user / pass / md5: "
						+ username + ", " + password + ", " + md5);

				resp = new ResponseLogin(md5, username);
			} else {
				// Smth failed during login. TODO: Check when this can happen
				System.out.println("Unexpected Login Error");
				resp = new ResponseLogin();
				resp.setErrorCode(ERROR_CODES.UnknownError);
			}
		} else {
			// User is not present in DB
			resp = new ResponseLogin();
			resp.setErrorCode(ERROR_CODES.UserNotInDB);
			System.out.println("Error: User not in DB, username: " + username);
		}
		return resp;
	}

	private boolean checkIfUserExists(String username, String password) {
		/* TODO: Check if User is valid in DB */
		//Todo DB
		if (username.equals("Hello") && password.equals("World"))
			return true;

		return true;
	}

	public ResponseStringStringMap getClasses(String authMD5){
		ResponseStringStringMap resp = new ResponseStringStringMap();

		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			String[] key = new String[4];
			key[0] = "0";
			key[1] = "1";
			key[2] = "2";
			key[3] = "3";
			
			String[] value = new String[4];
			
			value[0] = "11FI1";
			value[1] = "11FI2";
			value[2] = "11FI3";
			value[3] = "11FI4";
			
			resp.setValues(key,value);
		}

		return resp;
	}
	
	public ResponseStringStringMap getStudents(String authMD5){
		ResponseStringStringMap resp = new ResponseStringStringMap();

		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			String[] key = new String[4];
			key[0] = "0";
			key[1] = "1";
			key[2] = "2";
			key[3] = "3";
			
			String[] value = new String[4];
			
			value[0] = "Hans";
			value[1] = "Petzer";
			value[2] = "PedoRick";
			value[3] = "Steinam";
			
			resp.setValues(key,value);
		}

		return resp;
	}
	
	public ResponseString getStudentName(String authMD5, int studentID){
		ResponseString resp = new ResponseString();

		//Todo DB
		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			String studentName = StudentData.getStudentFirstName(studentID) + " " + StudentData.getStudentLastName(studentID);
			
			resp.setValue(studentName);
		}

		return resp;
	}
	
	public ResponseString getStudentClassId(String authMD5, int studentID){
		ResponseString resp = new ResponseString();


		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			Integer classId = StudentData.getStudentClass(studentID);
			
			resp.setValue(classId.toString());
		}

		return resp;
	}
	
	public ResponseInt getStudentCountByClassId(Integer classId){
		ResponseInt resp = new ResponseInt(10);
	
		return resp;
	}
	
	public ResponseInt addStudent(
			String authMD5,	
			String firstName, 
			String lastName, 
			String birthdate, 
			//int contactId, 
			int classId, 
			String email, 
			String phoneNumber, 
			String adress,
			String gender
			){
		
		ResponseInt resp = new ResponseInt();
		
		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			Date parsedBirthdate = new Date(0);
			
			StudentData.addStudent(firstName, lastName, parsedBirthdate, classId, email, phoneNumber, gender);
			
			resp.setValue(1337);
		}
		
		return resp;
	}
	
	public ResponseStringArray getStudentAbsents(String authMD5, int studentId){
		
		ResponseStringArray resp = new ResponseStringArray();
		if (userAuth.checkAthentification(authMD5, 0, resp)) {
			String[] absents = new String[6];
			absents[0] = "0";
			absents[1] = "1";
			absents[2] = "2";
			absents[3] = "3";
			absents[4] = "4";
			absents[5] = "5";
			
			resp.setValue(absents);
		}
		
		return resp;
		
	}	
}