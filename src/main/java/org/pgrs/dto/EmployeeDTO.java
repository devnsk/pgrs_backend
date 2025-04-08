package org.pgrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Employee entity.
 * This class is used to expose only required fields of the Employee entity
 * when sending or receiving data through APIs.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    /**
     * The unique identifier for the employee.
     */
    private Long id;

    /**
     * The ID of the associated user entity.
     */
    private Long userId;

    /**
     * First name of the employee.
     */
    private String firstName;

    /**
     * Last name of the employee.
     */
    private String lastName;

    /**
     * The ID of the department to which the employee belongs.
     */
    private Long departmentId;

    /**
     * Email address of the employee (could be fetched from User entity).
     */
    private String email;

    // Additional fields can be added here if needed for responses
}
