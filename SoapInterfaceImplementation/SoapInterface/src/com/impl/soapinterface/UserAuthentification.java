package com.impl.soapinterface;

import java.util.HashMap;

import com.impl.soapinterface.ResponseBase.ERROR_CODES;

public class UserAuthentification {
	private HashMap<String, String> userAuthList;
	
	private int getPermissionLevel(String username){
		//TODO Get Permission Level for username
		return 0;
	}
	
	public UserAuthentification(){
		userAuthList = new HashMap<String,String>();
	}
	
	public boolean checkIfAuthenticated(String md5){
		 if(userAuthList.containsValue(md5)) return true;
		 return false;
	}
	
	public boolean checkIfCallIsValid(String md5, int requiredPermissionLevel){
		if(getPermissionLevel(userAuthList.get(md5)) >= requiredPermissionLevel) return true;
		return false;
	}
	
	public boolean checkAthentification(String authMD5, int requiredPermissionLevel, ResponseBase respBase){
		if (!checkIfAuthenticated(authMD5)){
			respBase.setErrorCode(ERROR_CODES.UserNotLoggedIn);
		}else{
			if(!checkIfCallIsValid(authMD5, 0)){
				respBase.setErrorCode(ERROR_CODES.UserLacksPermission);
			}else{
				return true;
			}
		}
		return false;
	}
	
	public boolean authenticateUser(String username, String md5) {
		if(!userAuthList.containsKey(username)){
			
			//TODO Get user permission level from "the back"
			
			userAuthList.put(username, md5);
			
			for(int i = 0; i < userAuthList.size(); i++){
				System.out.println("#"+i+": "+userAuthList.values().toArray()[i].toString());
			}
			
			return true;
		}
		else
		{
			//TODO: WHAT TO DO IF ALREADY AUTHED
			
			userAuthList.remove(username);
			
			System.out.println("User already logged in, kicking out for new session: "+md5);
			
			for(int i = 0; i < userAuthList.size(); i++){
				System.out.println("#"+i+": "+userAuthList.values().toArray()[i].toString());
			}
			
			return authenticateUser(username, md5);
		}
	}
	
	public boolean logOutUser(String md5){
		if(userAuthList.containsValue(md5)){
			
			userAuthList.values().remove(md5);
			return true;
		}
		return false;
	}
}
