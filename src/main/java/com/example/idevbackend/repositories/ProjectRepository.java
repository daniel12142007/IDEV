package com.example.idevbackend.repositories;

import com.example.idevbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}