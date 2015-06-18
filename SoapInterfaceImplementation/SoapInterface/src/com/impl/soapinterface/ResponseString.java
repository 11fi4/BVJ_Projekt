package com.impl.soapinterface;

public class ResponseString extends ResponseBase {

	public ResponseString(){}
	public ResponseString(String _value){
		value = _value;
	}
	
	public String value = "";
	public String getValue(){
		return value;
	}
	
	public void setValue(String _value){
		value = _value;
	}
	
}
