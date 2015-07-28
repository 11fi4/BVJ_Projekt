package com.impl.soapinterface;

import com.impl.database.connection.access.DBAccessImpl;
import com.impl.database.elements.UserAccount;;

public class UserData {

	public static void addUser(String firstName, String lastName, Integer permissionId, String username, String password){
		SoapConnectionManager.dbAccess.insertUser(firstName, lastName, username, password);
	}
	
	public static int getUserId(String username, String password){
		UserAccount account = SoapConnectionManager.dbAccess.requestUser(username, password);
		
		return account.getUserAccount_id();
	}
}
