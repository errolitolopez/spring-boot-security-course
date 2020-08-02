package com.example.springbootsecuritycourse.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.form.UserForm;
import com.example.springbootsecuritycourse.model.User;
import com.example.springbootsecuritycourse.resource.UserResource;

@Service
public class UserMapper {

	private final RoleMapper roleMapper;

	@Autowired
	public UserMapper(RoleMapper roleMapper) {
		super();
		this.roleMapper = roleMapper;
	}

	public User map(UserForm userForm) {
		return new User(
				null,
				userForm.getEmail(),
				userForm.getLastName(),
				userForm.getFirstName(),
				userForm.getUsername(),
				userForm.getPassword(),
				userForm.getRoles()
						.stream()
						.map(roleMapper::map)
						.collect(Collectors.toSet())
		);
	}

	public UserResource map(User user) {
		return new UserResource(
				user.getUserId(),
				user.getEmail(),
				user.getLastName(),
				user.getFirstName(),
				user.getUsername(),
				user.getPassword(),
				user.getRoles()
						.stream()
						.map(roleMapper::map)
						.collect(Collectors.toSet())
		);
	}
}
