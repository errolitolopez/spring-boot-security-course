package com.example.springbootsecuritycourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.model.CustomUserDetails;
import com.example.springbootsecuritycourse.model.User;
import com.example.springbootsecuritycourse.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(@Qualifier("userRepository") UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException(String.format("User with username %s not found", username)));

		return new CustomUserDetails(
				user.getUserId(),
				user.getEmail(),
				user.getLastName(),
				user.getFirstName(),
				user.getUsername(),
				user.getPassword(),
				user.getRoles()
		);
	}
}
