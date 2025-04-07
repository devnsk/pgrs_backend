package org.pgrs.service;

import org.pgrs.dto.EmployeeDto;
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

    public Employee createEmployee(Employee employee) {
        // Validate and fetch User
        if (employee.getUser() == null || employee.getUser().getId() == null) {
            throw new RuntimeException("User ID is missing in the request");
        }
        User user = userRepository.findById(employee.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + employee.getUser().getId()));
        employee.setUser(user);

        // Validate and fetch Department
        if (employee.getDepartment() == null || employee.getDepartment().getId() == null) {
            throw new RuntimeException("Department ID is missing in the request");
        }
        Department department = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + employee.getDepartment().getId()));
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }


    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::toDto) // Use the mapper to convert entity to DTO
                .collect(Collectors.toList());
    }
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        existing.setFirstName(updatedEmployee.getFirstName());
        existing.setLastName(updatedEmployee.getLastName());

        if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(updatedEmployee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(dept);
        }

        if (updatedEmployee.getUser() != null && updatedEmployee.getUser().getId() != null) {
            User user = userRepository.findById(updatedEmployee.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(user);
        }

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
