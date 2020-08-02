package com.example.springbootsecuritycourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.model.User;
import com.example.springbootsecuritycourse.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void createUser(User user) {
		Optional<User> selectFindByEmail = userRepository.findByEmail(user.getEmail());
		Optional<User> selectFindByUsername = userRepository.findByUsername(user.getUsername());

		if (selectFindByEmail.isPresent()) {
			throw new RuntimeException(String.format("Email %s was already used.", user.getEmail()));
		}

		if (selectFindByUsername.isPresent()) {
			throw new RuntimeException(String.format("Username %s was already used.", user.getUsername()));
		}

		userRepository.save(new User(user.getUserId(),
				user.getEmail(),
				user.getLastName(),
				user.getFirstName(),
				user.getUsername(),
				bCryptPasswordEncoder.encode(user.getPassword()),
				user.getRoles())
		);
	}

	public User getUserById(Integer userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(String.format("User with id %s was not found.", userId)));
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void updateUser(User user, Integer userId) {
		Optional<User> selectFindByEmail = userRepository.findByEmail(user.getEmail());
		Optional<User> selectFindByUsername = userRepository.findByUsername(user.getUsername());
		Optional<User> selectFindById = userRepository.findById(userId);

		if (!selectFindById.isPresent()) {
			throw new RuntimeException(String.format("User with id %s was not found.", userId));
		}

		if (selectFindByEmail.isPresent()) {
			throw new RuntimeException(String.format("Email %s was already used.", user.getEmail()));
		}

		if (selectFindByUsername.isPresent()) {
			throw new RuntimeException(String.format("Username %s was already used.", user.getUsername()));
		}

		userRepository.save(
				new User(userId,
						user.getEmail(),
						user.getLastName(),
						user.getFirstName(),
						user.getUsername(),
						user.getPassword(),
						user.getRoles())
		);
	}

	public void deleteUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(
						String.format("Unable to delete user with id %s was not found.", userId))
				);
		userRepository.delete(user);
	}
}
