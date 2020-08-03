package com.example.springbootsecuritycourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecuritycourse.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUsername(String username);

}
