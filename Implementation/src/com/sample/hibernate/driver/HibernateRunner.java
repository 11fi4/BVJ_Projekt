/**
 * 
 */
package com.sample.hibernate.driver;

import com.sample.hibernate.dao.PersonsDAO;
import com.sample.hibernate.dto.Persons;

/**
 * @author Vienna
 *
 */
public class HibernateRunner {
	public static void main(String args[]){
		PersonsDAO personsDAO=new PersonsDAO();
		
		//Insert new record
		Persons persons3=new Persons();
		persons3.setPid(1);
		persons3.setFirstName("name");
		persons3.setLastName("last");
		persons3.setAddress("add");
		persons3.setCity("cc");
		personsDAO.insert(persons3);
		
		//Get existing record
		Persons persons=personsDAO.get(1);
		if(null!=persons){
			System.out.println(persons.toString());
		}else{
			System.out.println("No records found");
		}
		
		//Update existing record
		Persons persons2=new Persons();
		persons2.setPid(1);
		persons2.setFirstName("nameX");
		persons2.setLastName("lastX");
		persons2.setAddress("addX");
		persons2.setCity("ccX");
		personsDAO.update(persons2);
		
		//Get existing record
		Persons persons4=personsDAO.get(1);
		if(null!=persons4){
			System.out.println(persons4.toString());
		}else{
			System.out.println("No records found");
		}
	}
}
