package com.impl.soapinterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import com.impl.soapinterface.ResponseBase.ERROR_CODES;
import com.sun.jndi.ldap.EntryChangeResponseControl;

public class SoapConnectionManager {

	private static UserAuthentification userAuth;

	static boolean isInitialized = false;

	public SoapConnectionManager() {
		if (!isInitialized) {
			System.out.println("Initialising new Server session");
			userAuth = new UserAuthentification();
			isInitialized = true;
		}
	}

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
				resp.setErrorCode(ERROR_CODES.DUMMY);
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

		if (username.equals("Hello") && password.equals("World"))
			return true;

		return false;
	}

	public ResponseStringStringMap getClasses(){
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
		
		ResponseStringStringMap resp = new ResponseStringStringMap(key, value);
		
		return resp;
	}
	
	public ResponseInt getStudentCountByClassId(Integer classId){
		ResponseInt resp = new ResponseInt(10);
	
		return resp;
	}
}