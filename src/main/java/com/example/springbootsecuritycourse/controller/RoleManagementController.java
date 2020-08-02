package com.example.springbootsecuritycourse.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsecuritycourse.form.RoleForm;
import com.example.springbootsecuritycourse.mapper.RoleMapper;
import com.example.springbootsecuritycourse.resource.RoleResource;
import com.example.springbootsecuritycourse.service.RoleService;

@RestController
@RequestMapping("api/v1/roles")
public class RoleManagementController {

	private final RoleService roleService;
	private final RoleMapper roleMapper;

	public RoleManagementController(RoleService roleService, RoleMapper roleMapper) {
		super();
		this.roleService = roleService;
		this.roleMapper = roleMapper;
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
	public List<RoleResource> getAllRoles() {
		return roleService.getAllRoles()
				.stream()
				.map(roleMapper::map)
				.collect(Collectors.toList());
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
	public void createRole(@RequestBody RoleForm roleForm) {
		roleService.createRole(roleMapper.map(roleForm));
	}

}
