package org.pgrs.mapper;

import org.pgrs.dto.EmployeeDTO;
import org.pgrs.entity.Employee;

/**
 * Utility class for converting between Employee entity and EmployeeDTO.
 * This helps in separating the internal entity structure from the API response/request structure.
 */
public class EmployeeMapper {

    /**
     * Converts an Employee entity to EmployeeDTO.
     * @param employee The Employee entity to be converted.
     * @return A DTO representing the employee's public data.
     */
    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();

        // Set values from entity to DTO
        dto.setId(employee.getId());
        dto.setUserId(employee.getUser() != null ? employee.getUser().getId() : null);
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null);
        dto.setEmail(employee.getUser() != null ? employee.getUser().getEmail() : null);

        return dto;
    }

    /**
     * Converts an EmployeeDTO to Employee entity.
     * This is usually used only in limited scenarios (e.g., test cases),
     * because proper mapping with user and department is handled in the service layer.
     *
     * @param dto The DTO to convert.
     * @return A partially constructed Employee entity.
     */
    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();

        // Set values from DTO to entity
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        // Note: user and department should be set in the service after fetching from DB

        return employee;
    }
}
