package com.project.employee_management.repo;
import com.project.employee_management.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.employee_management.model.LoginEntity;

public interface EmployeeRepos extends JpaRepository<UserEntity, Long>{
    UserEntity findByEmail(String email);
}
