package com.impl.soapinterface;

import java.util.HashSet;

public class UserAuthentification {
	private HashSet<String> userAuthList;
	
	public UserAuthentification(){
		userAuthList = new HashSet<String>();
	}
	
	public boolean checkIfAuthenticated(String md5){
		 if(userAuthList.contains(md5)) return true;
		 return false;
	}
	
	public boolean authenticateUser(String md5) {
		if(!userAuthList.contains(md5)){
			userAuthList.add(md5);
			
			Object[] debugS = userAuthList.toArray();
			
			for(int i = 0; i < userAuthList.size(); i++){
				System.out.println("#"+i+": "+debugS[i].toString());
			}
			
			return true;
		}
		else
		{
			//TODO: WHAT TO DO IF ALREADY AUTHED
			return false;
		}
	}
}
