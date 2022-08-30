package com.example.MyProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyProject.models.Project;

public interface MyProjectRepository extends JpaRepository<Project, Long> {
	Project findByTitle (final String title);

}
