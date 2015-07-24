/**
 * 
 */
package com.impl.database.elements;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "parent")
public class Parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "parent_id")
	protected int parent_id;
	@Column(name = "fisrtName", nullable = false)
	protected String firstName;
	@Column(name = "lastName", nullable = false)
	protected String lastName;
	@Column(name = "eMail")
	protected String eMail;
	@Column(name = "phone_number")
	protected String phoneNumber;
	@Column(name = "address")
	protected String address;
	@Column(name = "gender")
	protected String gender;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "parents")
	protected Set<Student> students = new HashSet<Student>();

	public Parent() {
	}

	public Set<Student> getStudents() {
		return this.students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parents_id) {
		this.parent_id = parents_id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
