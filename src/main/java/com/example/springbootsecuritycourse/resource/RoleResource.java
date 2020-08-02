package com.example.springbootsecuritycourse.resource;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonDeserialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoleResource {

	private final Integer roleId;
	private final String role;

	public Integer getRoleId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}

	public RoleResource(Integer roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	@Override
	public String toString() {
		return "RoleResource [roleId=" + roleId + ", role=" + role + "]";
	}
}
