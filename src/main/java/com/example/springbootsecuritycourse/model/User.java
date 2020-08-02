package com.example.springbootsecuritycourse.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Integer userId;
	@Column(name = "email")
	private final String email;
	@Column(name = "last_name")
	private final String lastName;
	@Column(name = "first_name")
	private final String firstName;
	@Column(name = "username")
	private final String username;
	@Column(name = "password")
	private final String password;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private final Set<Role> roles;

	public User(Integer userId,
			String email,
			String lastName,
			String firstName,
			String username,
			String password,
			Set<Role> roles) {
		super();
		this.userId = userId;
		this.email = email;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
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

	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	public User() {
		this.userId = null;
		this.email = "";
		this.lastName = "";
		this.firstName = "";
		this.username = "";
		this.password = "";
		this.roles = null;
	}
}
