package com.impl.soapinterface;

import java.util.HashMap;

public class ResponseStringStringMap extends ResponseBase{
	
	String[] key;
	String[] value;
	
	public ResponseStringStringMap(){
		
	}
	
	public ResponseStringStringMap(String[] _key, String[] _value){
		key = _key;
		value = _value;
	}
	
	public String[] getKey(){
		return key;
	}
	
	public String[] getValue(){
		return value;
	}
}