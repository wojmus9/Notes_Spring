package com.example.MyProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyProject.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole (final String role);
}
