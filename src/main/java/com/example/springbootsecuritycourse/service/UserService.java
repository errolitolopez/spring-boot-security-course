package com.example.springbootsecuritycourse.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springbootsecuritycourse.exception.NotFoundException;
import com.example.springbootsecuritycourse.exception.NotValidException;
import com.example.springbootsecuritycourse.model.User;
import com.example.springbootsecuritycourse.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder bCryptPasswordEncoder;

	private static final String ID_NOT_FOUND = "Id %s not found.";
	private static final String EMAIL_ALREADY_EXISTS = "Email %s already exists.";
	private static final String USERNAME_ALREADY_EXISTS = "Username %s already exists.";

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void createUser(User user) {
		boolean anyMatchEmail = anyMatchEmail(user.getEmail());
		boolean anyMatchUsername = anyMatchUsername(user.getUsername());

		if (anyMatchEmail) {
			throw new NotValidException(String.format(EMAIL_ALREADY_EXISTS, user.getEmail()));
		}

		if (anyMatchUsername) {
			throw new NotValidException(String.format(USERNAME_ALREADY_EXISTS, user.getUsername()));
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
				.orElseThrow(() -> new NotFoundException(String.format(ID_NOT_FOUND, userId)));
	}

	public List<User> getAllUsers() {
		return getAll();
	}

	public void updateUser(User user, Integer userId) {
		boolean anyMatchEmail = anyMatchEmail(user.getEmail());
		boolean anyMatchUsername = anyMatchUsername(user.getUsername());
		boolean anyMatchUserId = anyMatchUserId(userId);

		if (!anyMatchUserId) {
			throw new NotFoundException(String.format(ID_NOT_FOUND, userId));
		}

		if (anyMatchEmail) {
			throw new NotValidException(String.format(EMAIL_ALREADY_EXISTS, user.getEmail()));
		}

		if (anyMatchUsername) {
			throw new NotValidException(String.format(USERNAME_ALREADY_EXISTS, user.getUsername()));
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
		boolean anyMatchUserId = anyMatchUserId(userId);

		if (anyMatchUserId) {
			throw new NotFoundException(String.format(ID_NOT_FOUND, userId));
		}

		userRepository.deleteById(userId);
	}

	private boolean anyMatchUserId(Integer userId) {
		boolean anyMatchUserId = getAll().stream()
				.anyMatch(selectUser -> selectUser.getUserId().equals(userId));
		return anyMatchUserId;
	}

	private boolean anyMatchUsername(String username) {
		boolean anyMatchUsername = getAll().stream()
				.anyMatch(selectUser -> selectUser.getUsername().equals(username));
		return anyMatchUsername;
	}

	private boolean anyMatchEmail(String email) {
		boolean anyMatchEmail = getAll().stream()
				.anyMatch(selectUser -> selectUser.getEmail().equals(email));
		return anyMatchEmail;
	}

	private List<User> getAll() {
		return userRepository.findAll();
	}
}
