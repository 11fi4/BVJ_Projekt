package com.impl.soapinterface;

import java.io.Serializable;

public class ResponseBase implements Serializable {

	public enum ERROR_CODES {
		UserNotInDB(1), 
		UserAlreadyLoggedIn(2), 
		UserNotLoggedIn(3);
		
		int code = -10;
		private ERROR_CODES(int _code){
			code = _code;
		}
		public int getErrorCode(){
			return code;
		}
	}
	
	public int errorCode = 0;
	public int getErrorCode() {
		return errorCode;
	 }
	 
	 public void setErrorCode(ERROR_CODES _errorCode){
		 errorCode = _errorCode.getErrorCode();
	 }
}
