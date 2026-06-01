package com.project.employee_management.repo;

import com.project.employee_management.model.ActivityLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogRepo
        extends JpaRepository<ActivityLogEntity, Long> {
}