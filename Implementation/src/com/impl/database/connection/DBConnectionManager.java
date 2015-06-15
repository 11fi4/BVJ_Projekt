/**
 * 
 */
package com.impl.database.connection;

/**
 * @author kuslu
 *
 */
public interface DBConnectionManager {
	
	public void insert(Object object);

	public void delete(Object object);

	public void update(Object object);

	public Object get(Class clazz, Object object);

}
