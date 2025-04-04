package org.pgrs.dto;

import java.time.LocalDate;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRegistrationRequest {
    // Student Details
    @NotBlank(message = "First Name is required")
    private String studentFirstName;

    @NotBlank(message = "Last Name is required")
    private String studentLastName;

    
    private String studentEmail;

    @NotBlank(message = "Registration Number is required")
    private String registrationNumber;

    private String department;
    private String batch;
    private LocalDate dateOfBirth;

    // Parent Details
    @NotBlank(message = "Parent First Name is required")
    private String parentFirstName;

    @NotBlank(message = "Parent Last Name is required")
    private String parentLastName;

    @Email(message = "Invalid parent email")
    private String parentEmail;

    @NotBlank(message = "Parent Contact Number is required")
    private String parentContactNumber;

    private String relationship; // Father, Mother, Guardian

    // Password
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
