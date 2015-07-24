/**
 * 
 */
package com.impl.database.elements;

import java.io.Serializable;
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
@Table(name = "class")
public class Class implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "class_id")
	protected int class_id;
	@Column(name = "name")
	protected String name;
	@Column(name = "year")
	protected int year;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classes")
	protected Set<Student> students = new HashSet<Student>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "_class")
	protected Set<ClassUser> classUsers = new HashSet<ClassUser>();

	public Class() {
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<ClassUser> getClassUsers() {
		return classUsers;
	}

	public void setClassUsers(Set<ClassUser> classUsers) {
		this.classUsers = classUsers;
	}
	
}