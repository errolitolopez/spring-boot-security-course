package com.example.springbootsecuritycourse.form;

import java.util.Set;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonDeserialize
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserForm {

	private final String email;

	private final String lastName;

	private final String firstName;

	private final String username;

	private final String password;

	private final Set<RoleForm> roles;

	public UserForm(String email,
			String lastName,
			String firstName,
			String username,
			String password,
			Set<RoleForm> roles) {
		super();
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.roles = roles;
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

	public Set<RoleForm> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "UserForm [email=" + email + ", lastName=" + lastName + ", firstName=" + firstName + ", username="
				+ username + ", password=" + password + ", roles=" + roles + "]";
	}
}
