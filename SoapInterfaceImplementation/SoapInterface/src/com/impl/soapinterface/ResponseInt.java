package com.impl.soapinterface;

public class ResponseInt extends ResponseBase {

	public ResponseInt(){
		
	}
	
	public ResponseInt(int _value){
		value = _value;
	}
	
	public int value = Integer.MIN_VALUE;
}
