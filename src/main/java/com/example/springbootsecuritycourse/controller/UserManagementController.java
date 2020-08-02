package com.example.springbootsecuritycourse.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsecuritycourse.form.UserForm;
import com.example.springbootsecuritycourse.mapper.UserMapper;
import com.example.springbootsecuritycourse.resource.UserResource;
import com.example.springbootsecuritycourse.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserManagementController {

	private final UserService userService;
	private final UserMapper userMapper;

	@Autowired
	public UserManagementController(UserService userService, UserMapper userMapper) {
		super();
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public void createUser(@RequestBody UserForm userForm) {
		userService.createUser(userMapper.map(userForm));
	}

	@GetMapping()
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public List<UserResource> getAllUsers() {
		return userService.getAllUsers()
				.stream()
				.map(userMapper::map)
				.collect(Collectors.toList());
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
	public UserResource getUserById(@PathVariable Integer userId) {
		return userMapper.map(userService.getUserById(userId));
	}

	@PostMapping("/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void updateUser(@RequestBody UserForm userForm, @PathVariable Integer userId) {
		userService.updateUser(userMapper.map(userForm), userId);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
}
