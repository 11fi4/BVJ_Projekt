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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "role_description")
public class RoleDescription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "roleDescription_id", unique = true, nullable = false)
	protected int roleDescription_id;
	@Column(name = "description")
	protected String description;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roleDescription")
	public Set<Role> roles = new HashSet<Role>();

	public RoleDescription() {
	}

	public int getRoleDescription_id() {
		return roleDescription_id;
	}

	public void setRoleDescription_id(int roleDescription_id) {
		this.roleDescription_id = roleDescription_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
