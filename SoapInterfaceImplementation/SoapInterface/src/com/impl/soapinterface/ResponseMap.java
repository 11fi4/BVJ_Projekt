package com.impl.soapinterface;

import java.util.HashMap;

public class ResponseMap extends ResponseBase{
	
	HashMap map;
	
	public ResponseMap(){
		
	}
	
	public ResponseMap(HashMap<Object, Object> _map){
		map = _map;
	}
	
	public HashMap<Object, Object> getMap(){
		return map;
	}
}
