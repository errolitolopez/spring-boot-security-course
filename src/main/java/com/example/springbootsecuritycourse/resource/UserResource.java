package com.example.springbootsecuritycourse.resource;

import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResource {

	private final Integer id;
	private final String email;
	private final String lastName;
	private final String firstName;
	private final String username;
	private final String password;

	private final Set<RoleResource> roles;

	public UserResource(Integer id,
			String email,
			String lastName,
			String firstName,
			String username,
			String password,
			Set<RoleResource> roles) {
		super();
		this.id = id;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<RoleResource> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "UserResource [id=" + id + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
}
