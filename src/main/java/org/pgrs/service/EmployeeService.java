package org.pgrs.service;

import org.pgrs.dto.EmployeeDTO;
import org.pgrs.entity.Department;
import org.pgrs.entity.Employee;
import org.pgrs.entity.User;
import org.pgrs.mapper.EmployeeMapper;
import org.pgrs.repository.DepartmentRepository;
import org.pgrs.repository.EmployeeRepository;
import org.pgrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Fetch all employees from the database and convert them to DTOs
     *
     * @return List of EmployeeDTO objects
     */
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Create a new employee entry using the data from EmployeeDTO
     *
     * @param employeeDTO DTO containing employee data
     * @return EmployeeDTO of the saved employee
     */
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // ✅ Validate input IDs
        if (employeeDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (employeeDTO.getDepartmentId() == null) {
            throw new IllegalArgumentException("Department ID must not be null");
        }

        // Fetch user from repository
        User user = userRepository.findById(employeeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + employeeDTO.getUserId()));

        // Fetch department from repository
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + employeeDTO.getDepartmentId()));

        // Map DTO to Entity
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDepartment(department);

        // Save entity to database
        employee = employeeRepository.save(employee);

        // Convert saved entity back to DTO and return
        return EmployeeMapper.toDTO(employee);
    }

    /**
     * Fetch an employee by their ID
     *
     * @param id ID of the employee
     * @return Optional<Employee> object
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Update the fields of an existing employee
     *
     * @param id ID of the employee to update
     * @param updatedEmployee Employee object containing updated data
     * @return Updated Employee object
     */
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Retrieve existing employee
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        // Update fields
        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());

        // Update department if provided
        if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(updatedEmployee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(dept);
        }

        // Update user if provided
        if (updatedEmployee.getUser() != null && updatedEmployee.getUser().getId() != null) {
            User user = userRepository.findById(updatedEmployee.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(user);
        }

        // Save and return updated employee
        return employeeRepository.save(existing);
    }

    /**
     * Delete an employee by ID
     *
     * @param id ID of the employee to delete
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
