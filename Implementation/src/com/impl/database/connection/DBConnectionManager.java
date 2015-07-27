/**
 * 
 */
package com.impl.database.connection;

import org.hibernate.SessionFactory;

/**
 * @author kuslu
 *
 */
public interface DBConnectionManager {

	public void insert(Object object);

	public void delete(Object object);

	public void update(Object object);

	@SuppressWarnings("rawtypes")
	public Object get(Class clazz, Object object);

	public SessionFactory getSessionFactory();
	
	public void createHQLQuery(String query);
}
