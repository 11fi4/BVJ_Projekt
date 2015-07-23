package com.impl.soapinterface;

public class ResponseStringArray extends ResponseBase{
	
	String[] value;
	
	public ResponseStringArray(){
		
	}
	
	public ResponseStringArray(String[] _value){
		value = _value;
	}
	
	public String[] getValue(){
		return value;
	}
}
