package com.impl.soapinterface.responses;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseBase implements Serializable {

	public enum ERROR_CODES {
		UserNotInDB(1), 
		InsertionError(2), 
		UserNotLoggedIn(3),
		UserLacksPermission(4),
		UnknownError(999);
		
		
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
