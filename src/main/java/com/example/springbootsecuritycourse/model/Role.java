package com.example.springbootsecuritycourse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Integer roleId;

	@Column(name = "role")
	private final String role;

	public Integer getRoleId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}

	public Role(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	public Role() {
		this.roleId = null;
		this.role = null;
	}
}
