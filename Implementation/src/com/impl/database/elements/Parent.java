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
@Table(name = "PARENT")
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
	@Column(name = "name", nullable = false)
	protected String name;
	@Column(name = "contact_id")
	protected int contactId;
	@Column(name = "email")
	protected String eMail;
	@Column(name = "phone_number")
	protected String phoneNumber;
	@Column(name = "address")
	protected String address;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "parents")
	private Set<Student> students = new HashSet<Student>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
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
