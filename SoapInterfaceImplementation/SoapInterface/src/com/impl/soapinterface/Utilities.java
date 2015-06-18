package com.impl.soapinterface;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {
	public static String getMD5(String inputString){
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
}
