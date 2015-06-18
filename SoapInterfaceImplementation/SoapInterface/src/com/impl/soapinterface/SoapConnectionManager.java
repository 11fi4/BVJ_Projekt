package com.impl.soapinterface;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

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
	
	public ResponseInt return5(String authMD5) {
		if(userAuth.checkIfAuthenticated(authMD5)){
			System.out.println("Auth Check true for md5: "+authMD5);
			return new ResponseInt(5);
		}
		else{
			System.out.println("Auth Check false for md5: "+authMD5);
			ResponseInt resp = new ResponseInt();
			resp.setErrorCode(ERROR_CODES.UserNotLoggedIn);
			return resp;
		}
	}

	/*
	 * public ResponseBase returnHi(){
	 * 
	 * }
	 */

	public ResponseLogin logIn(String username, String password) {
		String md5 = "";
		ResponseLogin resp;
		
		System.out.println("Login Attempt, user / pass: "+username+", "+password);
		
		if(username.equals("Hallo") && password.equals("Welt") /*TODO: Check if User is valid in DB*/){
			md5 = Utilities.getMD5(password+username);
			
			if(userAuth.authenticateUser(md5)){
				System.out.println("Login Successfull, user / pass / md5: "+username+", "+password+", "+md5);
				resp = new ResponseLogin(md5,username);
			}else{
				System.out.println("User already logged in, for user: "+username);
				resp = new ResponseLogin();
				resp.setErrorCode(ERROR_CODES.UserAlreadyLoggedIn);
			}
		}
		else{
			//TODO Return ResponseString with error code
			resp = new ResponseLogin();
			resp.setErrorCode(ERROR_CODES.UserNotInDB);
		}
		return resp;
	}
}
