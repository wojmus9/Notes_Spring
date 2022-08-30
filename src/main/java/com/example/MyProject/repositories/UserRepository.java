package com.example.MyProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyProject.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail (final String email);


}
