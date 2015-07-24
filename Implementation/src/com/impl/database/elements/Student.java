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
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "student_id", unique = true, nullable = false)
	protected int student_id;
	@Column(name = "firstName", nullable = false)
	protected String firstName;
	@Column(name = "lastName", nullable = false)
	protected String lastName;
	@Column(name = "birthdate")
	protected Date birthdate;
	@Column(name = "gender")
	protected String gender;
	@Column(name = "eMail")
	protected String eMail;
	@Column(name = "phone_number")
	protected String phoneNumber;
	@Column(name = "address")
	protected String address;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_parent",//
	joinColumns = { @JoinColumn(name = "student_id", nullable = false, updatable = false) },//
	inverseJoinColumns = { @JoinColumn(name = "parent_id", nullable = false, updatable = false) })
	protected Set<Parent> parents = new HashSet<Parent>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	protected Set<Detension> detensions = new HashSet<Detension>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	protected Set<Comment> comments = new HashSet<Comment>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	protected Set<Warning> warnings = new HashSet<Warning>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	protected Set<Absent> absents = new HashSet<Absent>();
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_class",//
	joinColumns = { @JoinColumn(name = "student_id", nullable = false, updatable = false) },//
	inverseJoinColumns = { @JoinColumn(name = "class_id", nullable = false, updatable = false) })
	protected Set<Class> classes = new HashSet<Class>();

	public Student() {
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public Set<Parent> getParents() {
		return parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
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
		return absents;
	}

	public void setAbsents(Set<Absent> absents) {
		this.absents = absents;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

}
