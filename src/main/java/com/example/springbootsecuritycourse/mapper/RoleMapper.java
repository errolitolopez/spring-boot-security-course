package com.example.springbootsecuritycourse.mapper;

import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.form.RoleForm;
import com.example.springbootsecuritycourse.model.Role;
import com.example.springbootsecuritycourse.resource.RoleResource;

@Service
public class RoleMapper {

	public Role map(RoleForm roleForm) {
		return new Role(roleForm.getRoleId(), roleForm.getRole());
	}

	public RoleResource map(Role role) {
		return new RoleResource(role.getRoleId(), role.getRole());
	}
}
