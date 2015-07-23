package com.impl.soapinterface.responses;

public class ResponseBool extends ResponseBase {
	public ResponseBool(){
		
	}
	
	public ResponseBool(boolean _value){
		value = _value;
	}
	
	public boolean value = false;
	
	public boolean getValue(){
		return value;
	}
	public void setValue(boolean _value){
		value = _value;
	}
}
