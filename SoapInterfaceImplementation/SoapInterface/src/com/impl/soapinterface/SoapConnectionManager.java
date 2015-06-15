package com.impl.soapinterface;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

public class SoapConnectionManager {

	private static HashSet<String> userAuthList;
	
	public SoapConnectionManager(){
		if(userAuthList == null)
		userAuthList = new HashSet<String>();
	}
	
	public ResponseInt return5(String authMD5) {
		
		Object[] debugS = userAuthList.toArray();
		
		for(int i = 0; i < userAuthList.size(); i++){
			System.out.println("#"+i+": "+debugS[i].toString());
		}
		
		if(checkIfAuthenticated(authMD5)){
			System.out.println("Auth Check true for md5: "+authMD5);
			return new ResponseInt(5);
		}
		else{
			System.out.println("Auth Check false for md5: "+authMD5);
			ResponseInt resp = new ResponseInt();
			resp.errorCode = 1;
			resp.errorMessage = "Not Authed";
			return resp;
		}
	}

	/*
	 * public ResponseBase returnHi(){
	 * 
	 * }
	 */
	
	protected boolean checkIfAuthenticated(String md5){
		 if(userAuthList.contains(md5)) return true;

		 return false;
	}

	public String authenticateUser(String username, String password) {
		
		System.out.println("User: "+username);
		String md5 = "";
		//If user is valid in DB
		if(true){
			md5 = getMD5(password+username);
			System.out.println(md5+" for user / pass: "+username+", "+password);
			
			if(!userAuthList.contains(md5)){
				userAuthList.add(md5);
			}
			else
			{
				//TODO: WHAT TO DO IF ALREADY AUTHED
			}
			
		}
		
		return md5;
	}
	
	private static String getMD5(String inputString){
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] md = digest.digest(inputString.getBytes());
			BigInteger number = new BigInteger(1,md);
			String hashText = number.toString(16);
			
			while (hashText.length() < 32){
				hashText = "0" + hashText;
			}
			
			return hashText;
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	
	public int returnInputInt(Integer inputInt)
	{
		return inputInt;
	}
}
