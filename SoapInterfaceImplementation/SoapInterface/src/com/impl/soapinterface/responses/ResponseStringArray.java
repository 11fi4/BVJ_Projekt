package com.impl.soapinterface.responses;

@SuppressWarnings("serial")
public class ResponseStringArray extends ResponseBase{
	
	String[] value;
	
	public void setValue(String[] value) {
		this.value = value;
	}

	public ResponseStringArray(){
		
	}
	
	public ResponseStringArray(String[] _value){
		value = _value;
	}
	
	public String[] getValue(){
		return value;
	}
}
