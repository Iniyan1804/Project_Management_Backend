package com.project.employee_management.repo;

import com.project.employee_management.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo
        extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByStatus(String status);
}