package com.project.employee_management.repo;

import com.project.employee_management.model.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo
        extends JpaRepository<ProjectEntity, Long> {
}