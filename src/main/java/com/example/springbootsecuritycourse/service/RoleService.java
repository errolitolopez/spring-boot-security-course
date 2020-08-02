package com.example.springbootsecuritycourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.model.Role;
import com.example.springbootsecuritycourse.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public void createRole(Role role) {

		Optional<Role> selectFindByUserRole = roleRepository.findByRole(role.getRole());

		if (selectFindByUserRole.isPresent()) {
			throw new RuntimeException(String.format("Role %s is already existing.", role.getRole()));
		}

		roleRepository.save(role);
	}

	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	public Role getRoleById(Integer roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new RuntimeException(
						String.format("Role with id %s was not found.", roleId))
				);
	}

	public void updateRole(Role role, Integer roleId) {
		Optional<Role> selectFindByUserRole = roleRepository.findByRole(role.getRole());
		Optional<Role> selectFindById = roleRepository.findById(roleId);

		if (selectFindByUserRole.isPresent()) {
			throw new RuntimeException(String.format("Role %s is already existing.", role.getRole()));
		}

		if (!selectFindById.isPresent()) {
			throw new RuntimeException(String.format("Role with id %s was not found.", roleId));
		}

		roleRepository.save(
				new Role(roleId, role.getRole())
		);
	}
}
