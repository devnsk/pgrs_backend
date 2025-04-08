package org.pgrs.controller;

import org.pgrs.dto.EmployeeDTO;
import org.pgrs.entity.Employee;
import org.pgrs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees") // Base URL for all employee-related APIs
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * API to fetch all employees.
     * @return List of all employees as DTOs.
     */
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * API to create a new employee.
     * @param employeeDTO Contains the details of the employee to create.
     * @return The created employee as a DTO.
     */
    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    /**
     * API to fetch a single employee by ID.
     * @param id Employee ID
     * @return The employee entity if found, wrapped in Optional
     */
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * API to update an existing employee.
     * @param id ID of the employee to update.
     * @param updatedEmployee Contains updated employee details.
     * @return The updated employee entity.
     */
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    /**
     * API to delete an employee by ID.
     * @param id ID of the employee to delete.
     * @return A success message after deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
