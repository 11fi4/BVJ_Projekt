package com.impl.soapinterface.responses;

public class ResponseLogin extends ResponseBase {

	public ResponseLogin(){}
	public ResponseLogin(String _md5, String _username){
		md5 = _md5;
		username = _username;
	}
	
	public String md5 = "";
	public String getMd5(){
		return md5;
	}
	
	public String username = "";
	public String getUsername(){
		return username;
	}
	
}
