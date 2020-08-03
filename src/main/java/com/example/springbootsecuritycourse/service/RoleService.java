package com.example.springbootsecuritycourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.exception.NotFoundException;
import com.example.springbootsecuritycourse.exception.NotValidException;
import com.example.springbootsecuritycourse.model.Role;
import com.example.springbootsecuritycourse.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private static final String ROLE_ALREADY_EXISTS = "Role %s already exists.";
	private static final String ROLE_NOT_FOUND = "Id %s not found.";

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public void createRole(Role role) {
		boolean anyMatchRole = anyMatchRole(role.getRole());

		if (anyMatchRole) {

			throw new NotValidException(String.format(ROLE_ALREADY_EXISTS, role.getRole()));
		}

		roleRepository.save(role);
	}

	public List<Role> getAllRoles() {
		return getAll();
	}

	public Role getRoleById(Integer roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new NotFoundException(String.format(ROLE_NOT_FOUND, roleId)));
	}

	public void updateRole(Role role, Integer roleId) {
		boolean anyMatchRole = anyMatchRole(role.getRole());
		boolean anyMatchRoleId = anyMatchId(roleId);

		if (anyMatchRole) {
			throw new NotValidException(String.format(ROLE_ALREADY_EXISTS, role.getRole()));
		}

		if (!anyMatchRoleId) {
			throw new NotFoundException(String.format(ROLE_NOT_FOUND, roleId));
		}

		roleRepository.save(new Role(roleId, role.getRole()));
	}

	private List<Role> getAll() {
		return roleRepository.findAll();
	}

	private boolean anyMatchRole(String role) {
		return getAll().stream()
				.anyMatch(selectRole -> selectRole.getRole().equals(role));
	}

	private boolean anyMatchId(Integer roleId) {
		return getAll().stream()
				.anyMatch(selectRole -> selectRole.getRoleId().equals(roleId));
	}
}
