package com.impl.database.elements;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "userAccount")
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "userAccount_id", unique = true, nullable = false)
	protected int userAccount_id;
	@Column(name = "name", nullable = false)
	protected String name;
	@Column(name = "username")
	protected String username;
	@Column(name = "password")
	protected String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	protected Set<ClassUser> classUsers = new HashSet<ClassUser>();

	public UserAccount() {
	}

	
	public int getUserAccount_id() {
		return userAccount_id;
	}


	public void setUserAccount_id(int userAccount_id) {
		this.userAccount_id = userAccount_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<ClassUser> getClassUsers() {
		return classUsers;
	}

	public void setClassUsers(Set<ClassUser> classUsers) {
		this.classUsers = classUsers;
	}

}