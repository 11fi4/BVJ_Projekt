package com.impl.soapinterface.responses;

@SuppressWarnings("serial")
public class ResponseInt extends ResponseBase {

	public ResponseInt(){
		
	}
	
	public ResponseInt(int _value){
		value = _value;
	}
	
	public int value = Integer.MIN_VALUE;
	
	public int getValue(){
		return value;
	}
	public void setValue(int _value){
		value = _value;
	}
}
