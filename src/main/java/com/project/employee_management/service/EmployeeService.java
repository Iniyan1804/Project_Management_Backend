package com.project.employee_management.service;

import com.project.employee_management.model.*;
import com.project.employee_management.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepos employeeRepos;

    @Autowired
    private ProjectRepo projectRepos;

    @Autowired
    private TaskRepo taskRepos;

    @Autowired
    private ActivityLogRepo activityLogRepos;

    // LOGIN
    public Boolean login(LoginEntity loginEntity) {

        UserEntity user =
                employeeRepos.findByEmail(loginEntity.getEmail());

        if (user != null &&
                user.getPassword().equals(loginEntity.getPassword())) {
            return true;
        }

        return false;
    }

    // SIGNUP
    public String signup(UserEntity user) {

        UserEntity existingUser =
                employeeRepos.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "User already exists";
        }

        employeeRepos.save(user);

        return "Signup successful";
    }

    // FORGOT PASSWORD
    public String forgotPassword(LoginEntity user) {

        UserEntity existingUser =
                employeeRepos.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "Password reset link sent";
        }

        return "Email not found";
    }

    // DASHBOARD STATS
    public Map<String, Long> getDashboardStats() {

        Map<String, Long> stats = new HashMap<>();

        long totalEmployees = employeeRepos.count();
        long totalProjects = projectRepos.count();
        long totalTasks = taskRepos.count();
        long completedTasks =
                taskRepos.findByStatus("DONE").size();

        stats.put("employees", totalEmployees);
        stats.put("projects", totalProjects);
        stats.put("tasks", totalTasks);
        stats.put("completed", completedTasks);

        return stats;
    }

    // CREATE PROJECT
    public String createProject(ProjectEntity project) {

        projectRepos.save(project);

        ActivityLogEntity log =
                new ActivityLogEntity();

        log.setAction("Project Created");

        activityLogRepos.save(log);

        return "Project created successfully";
    }

    // GET ALL PROJECTS
    public List<ProjectEntity> getProjects() {
        return projectRepos.findAll();
    }

    // CREATE TASK
    public String createTask(TaskEntity task) {

        taskRepos.save(task);

        ActivityLogEntity log =
                new ActivityLogEntity();

        log.setTaskId(task.getId());
        log.setAction("Task Created");

        activityLogRepos.save(log);

        return "Task created successfully";
    }

    // GET ALL TASKS
    public List<TaskEntity> getTasks() {
        return taskRepos.findAll();
    }

    // UPDATE TASK STATUS
    public String updateTaskStatus(Long id, String status) {

        Optional<TaskEntity> optionalTask =
                taskRepos.findById(id);

        if (optionalTask.isPresent()) {

            TaskEntity task = optionalTask.get();

            task.setStatus(status);

            taskRepos.save(task);

            ActivityLogEntity log =
                    new ActivityLogEntity();

            log.setTaskId(id);
            log.setAction(
                    "Task status updated to " + status
            );

            activityLogRepos.save(log);

            return "Task updated successfully";
        }

        return "Task not found";
    }
    public List<UserEntity> getUsers() {
        return employeeRepos.findAll();
    }
    public String updateRole(Long id, String role) {

        Optional<UserEntity> optionalUser =
                employeeRepos.findById(id);

        if (optionalUser.isPresent()) {

            UserEntity user = optionalUser.get();

            user.setRole(role);

            employeeRepos.save(user);

            return "Role updated successfully";
        }

        return "User not found";
    }

}