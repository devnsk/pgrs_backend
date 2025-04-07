package org.pgrs.mapper;

import org.pgrs.dto.EmployeeDto;
import org.pgrs.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());

        if (employee.getUser() != null) {
            dto.setEmail(employee.getUser().getEmail());
        }

        if (employee.getDepartment() != null) {
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        return dto;
    }
}
