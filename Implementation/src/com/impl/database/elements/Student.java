/**
 * 
 */
package com.impl.database.elements;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "STUDENT")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "student_id", unique = true, nullable = false)
	protected int student_id;

	@Column(name = "name", nullable = false)
	protected String name;
	@Column(name = "birthdate")
	protected Date birthdate;
	@Column(name = "gender")
	protected String gender;
	@Column(name = "EMail")
	protected String EMail;
	@Column(name = "phone_number")
	protected String phoneNumber;
	@Column(name = "address")
	protected String address;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "STUDENT_PARENT",//
	joinColumns = { @JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false) },//
	inverseJoinColumns = { @JoinColumn(name = "PARENT_ID", nullable = false, updatable = false) })
	protected Set<Parent> parents = new HashSet<Parent>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Detension> detensions = new HashSet<Detension>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Comment> comments = new HashSet<Comment>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Warning> warnings = new HashSet<Warning>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Absent> Absents = new HashSet<Absent>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "STUDENT_CLASS",//
	joinColumns = { @JoinColumn(name = "STUDENT_ID", nullable = false, updatable = false) },//
	inverseJoinColumns = { @JoinColumn(name = "CLASS_ID", nullable = false, updatable = false) })
	protected Set<Class> classes = new HashSet<Class>();

	public Student() {
	}

	public Set<Parent> getParents() {
		return this.parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}


	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Detension> getDetensions() {
		return detensions;
	}

	public void setDetensions(Set<Detension> detensions) {
		this.detensions = detensions;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Warning> getWarnings() {
		return warnings;
	}

	public void setWarnings(Set<Warning> warnings) {
		this.warnings = warnings;
	}

	public Set<Absent> getAbsents() {
		return Absents;
	}

	public void setAbsents(Set<Absent> absents) {
		Absents = absents;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

}
