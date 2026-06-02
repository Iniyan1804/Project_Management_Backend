package com.project.employee_management.contoller;

import com.project.employee_management.model.*;
import com.project.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = {"http://localhost:5173","https://your-app.vercel.app"})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // LOGIN
    @PostMapping("/login")
    public Boolean login(@RequestBody LoginEntity userLogin) {
        return employeeService.login(userLogin);
    }

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody UserEntity user) {
        return employeeService.signup(user);
    }

    // FORGOT PASSWORD
    @PostMapping("/forgotPassword")
    public String forgotPassword(@RequestBody LoginEntity user) {
        return employeeService.forgotPassword(user);
    }

    // DASHBOARD STATS
    @GetMapping("/dashboard")
    public Map<String, Long> dashboard() {
        return employeeService.getDashboardStats();
    }

    // CREATE PROJECT
    @PostMapping("/project")
    public String createProject(
            @RequestBody ProjectEntity project
    ) {
        return employeeService.createProject(project);
    }

    // GET ALL PROJECTS
    @GetMapping("/projects")
    public List<ProjectEntity> getProjects() {
        return employeeService.getProjects();
    }

    // CREATE TASK
    @PostMapping("/task")
    public String createTask(
            @RequestBody TaskEntity task
    ) {
        return employeeService.createTask(task);
    }

    // GET ALL TASKS
    @GetMapping("/tasks")
    public List<TaskEntity> getTasks() {
        return employeeService.getTasks();
    }

    // UPDATE TASK STATUS
    @PutMapping("/task/{id}")
    public String updateTaskStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return employeeService.updateTaskStatus(id, status);
    }
    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return employeeService.getUsers();
    }
    @PutMapping("/user/{id}")
    public String updateRole(
            @PathVariable Long id,
            @RequestParam String role
    ) {
        return employeeService.updateRole(id, role);
    }

}