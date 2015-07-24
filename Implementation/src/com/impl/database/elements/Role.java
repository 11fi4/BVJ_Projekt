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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "role_id", unique = true, nullable = false)
	protected int role_id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleDescription_id", nullable = false)
	protected RoleDescription roleDescription;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
	public Permission permission;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	protected ClassUser classUser;

	public Role() {
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public RoleDescription getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(RoleDescription roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public ClassUser getClassUser() {
		return classUser;
	}

	public void setClassUser(ClassUser classUser) {
		this.classUser = classUser;
	}

}
