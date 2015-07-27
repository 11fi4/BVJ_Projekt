package com.impl.soapinterface;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.impl.database.connection.access.DBAccessImpl;
import com.impl.soapinterface.responses.ResponseBase;
import com.impl.soapinterface.responses.ResponseBase.ERROR_CODES;

public class UserAuthentification {

	// In milliseconds
	private static final long sessionTimeout = 10 * 60 * 1000;

	private class UserAuthentificationInformation {
		public UserAuthentificationInformation(String _username,
				long _loginTimestamp) {
			username = _username;
			loginTimestamp = _loginTimestamp;
		}

		public String username;
		public long loginTimestamp;
	}

	private HashMap<String, UserAuthentificationInformation> userAuthList;

	private int getPermissionLevel(String username) {
		// TODO Get Permission Level for username
		return 0;
	}

	public UserAuthentification() {
		// Init the session dictionary
		userAuthList = new HashMap<String, UserAuthentificationInformation>();

		// Init session timeouts
		SessionTimer sessionTimeoutThread = new SessionTimer();
		sessionTimeoutThread.start();

	}

	public boolean checkIfAuthenticated(String md5) {
		if (userAuthList.containsKey(md5))
			return true;
		return false;
	}

	public boolean checkIfCallIsAllowed(String md5, int requiredPermissionLevel) {
		if (getPermissionLevel(userAuthList.get(md5).username) >= requiredPermissionLevel)
			return true;
		return false;
	}

	public boolean checkAthentification(String authMD5,
			int requiredPermissionLevel, ResponseBase respBase) {
		if (!checkIfAuthenticated(authMD5)) {
			respBase.setErrorCode(ERROR_CODES.UserNotLoggedIn);
		} else {
			resetSessionTimeout(userAuthList.get(authMD5));

			if (!checkIfCallIsAllowed(authMD5, 0)) {
				respBase.setErrorCode(ERROR_CODES.UserLacksPermission);
			} else {
				return true;
			}
		}
		return false;
	}

	private void resetSessionTimeout(UserAuthentificationInformation authInfo) {
		authInfo.loginTimestamp = new Date().getTime();
		System.out.println("Resetting timeout for user: " + authInfo.username);
	}

	private String getMD5forUsername(String username) {
		String keyForUser = "";
		for (Map.Entry<String, UserAuthentificationInformation> entry : userAuthList
				.entrySet()) {
			String key = entry.getKey();
			UserAuthentificationInformation authInfo = entry.getValue();

			if (authInfo.username.equals(username)) {
				keyForUser = key;
				break;
			}
		}
		return keyForUser;
	}

	private void debugCurrentlyLoggedinUsers() {
		System.out.println("Currently Logged In user count: "
				+ userAuthList.size());
		for (int i = 0; i < userAuthList.size(); i++) {
			System.out.println("#" + i + ": "
					+ userAuthList.keySet().toArray()[i].toString());
		}
	}

	public boolean checkIfUserExists(String username, String password) {		
		if(SoapConnectionManager.dbAccess.getUser(username, password) != null){
			System.out.println("User is really valid in DB");
			return true;
		}
		
		return true;
	}
	
	public boolean authenticateUser(String username, String md5) {
		// Get if the user is already logged in.
		// If the username is not found, the return string will be "";
		String md5ForLoggedinUsername = getMD5forUsername(username);

		if (md5ForLoggedinUsername.equals("")) {
			UserAuthentificationInformation userAuthInf = new UserAuthentificationInformation(
					username, new Date().getTime());

			userAuthList.put(md5, userAuthInf);

			debugCurrentlyLoggedinUsers();

			return true;
		} else {
			// User is already authenticated

			// Check the Map for username
			userAuthList.remove(md5ForLoggedinUsername);

			System.out
					.println("User already logged in, kicking out for new session: "
							+ md5);

			for (int i = 0; i < userAuthList.size(); i++) {
				System.out.println(
						"#"
						+ i
						+ ": "
						+ ((UserAuthentificationInformation) userAuthList
								.values().toArray()[i]).username);
			}

			return authenticateUser(username, md5);
		}
	}

	public boolean logOutUser(String md5) {
		if (userAuthList.containsKey(md5)) {

			System.out.println("Logging out user: " + md5 + ", name: "
					+ userAuthList.get(md5).username);
			userAuthList.remove(md5);

			debugCurrentlyLoggedinUsers();

			return true;
		}
		return false;
	}

	private void checkActiveSessionsForTimeout() {

		if(!userAuthList.isEmpty()){
			System.out.println("Checking for Session Timeouts");

			for (Map.Entry<String, UserAuthentificationInformation> entry : userAuthList
					.entrySet()) {
				String key = entry.getKey();
				UserAuthentificationInformation authInfo = entry.getValue();

				if (new Date().getTime() > authInfo.loginTimestamp + sessionTimeout) {
					// Log timeout
					System.out.println("Session for user: " + authInfo.username
							+ " expired. Logging out");
					logOutUser(key);

					break;
				}
			}
		}

	}

	private class SessionTimer extends Thread {
		public SessionTimer() {
			System.out.println("Starting Session Timer");
		}

		public void run() {
			while (true) {
				// Wait for one minute
				try {
					sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				checkActiveSessionsForTimeout();
			}
		}
	}
}
