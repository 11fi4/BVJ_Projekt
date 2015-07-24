package com.impl.database.elements;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "class_user")
public class ClassUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "classUser_id", unique = true, nullable = false)
	protected int classUser_id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", nullable = false)
	protected Class _class;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userAccount_id", nullable = false)
	protected UserAccount userAccount;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "classUser", cascade = CascadeType.ALL)
	protected Role role;

	public ClassUser() {
	}

	public int getClassUser_id() {
		return classUser_id;
	}

	public void setClassUser_id(int classUser_id) {
		this.classUser_id = classUser_id;
	}

	public Class get_class() {
		return _class;
	}

	public void set_class(Class _class) {
		this._class = _class;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
