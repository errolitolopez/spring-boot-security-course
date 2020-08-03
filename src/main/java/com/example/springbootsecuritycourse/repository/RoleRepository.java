package com.example.springbootsecuritycourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsecuritycourse.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
