package com.impl.soapinterface;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;

import org.apache.tomcat.jni.Time;

import com.impl.soapinterface.ResponseBase.ERROR_CODES;

public class SoapConnectionManager {
	
	private static UserAuthentification userAuth;
	
	static boolean isInitialized = false;
	
	public SoapConnectionManager(){
		if(!isInitialized)
		{
			userAuth = new UserAuthentification();
			isInitialized = true;
		}
	}
	
	//Dummy return
	//This class represents the implementation of any method call.
	//Please refer to this structure if implementing new methods
	public ResponseInt return5(String authMD5) {
		ResponseInt resp = new ResponseInt();
		
		if(userAuth.checkAthentification(authMD5, 0, resp)){
			resp.setValue(5);
		}
		
		return resp;
	}
	
	public ResponseBool logOut(String authMD5){
		ResponseBool resp = new ResponseBool();
		
		if(userAuth.checkAthentification(authMD5, 0, resp)){
			resp.setValue(userAuth.logOutUser(authMD5));
		}
		return resp;
	}

	public ResponseLogin logIn(String username, String password) {
		ResponseLogin resp;
		
		System.out.println("Login Attempt, user / pass: "+username+", "+password);
		
		if(username.equals("Hallo") && password.equals("Welt") /*TODO: Check if User is valid in DB*/){
			//User is present in DB
			String md5 = Utilities.getMD5(password+username+String.valueOf(new Date().getTime()));
			
			if(userAuth.authenticateUser(username,md5)){
				System.out.println("Login Successfull, user / pass / md5: "+username+", "+password+", "+md5);
				
				resp = new ResponseLogin(md5,username);
			}else{
				
				System.out.println("User already logged in, for user: "+username);
				
				resp = new ResponseLogin();
				resp.setErrorCode(ERROR_CODES.UserAlreadyLoggedIn);
			}
		}
		else{
			//User is not present in DB
			resp = new ResponseLogin();
			resp.setErrorCode(ERROR_CODES.UserNotInDB);
		}
		return resp;
	}
	
	
}
