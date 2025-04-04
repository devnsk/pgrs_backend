package org.pgrs.service;

import java.util.List;
import java.util.Optional;

import org.pgrs.dto.StudentRegistrationRequest;
import org.pgrs.dto.response.StudentResponse;

public interface StudentService {
	StudentResponse registerStudent(StudentRegistrationRequest request);

    // Retrieve
    Optional<StudentResponse> getStudentById(Long id);
    Optional<StudentResponse> getStudentByRegistrationNumber(String registrationNumber);
    List<StudentResponse> getAllStudents();
    List<StudentResponse> getStudentsByDepartment(String department);
    List<StudentResponse> getStudentsByBatch(String batch);

    // Update
    StudentResponse updateStudent(Long id, StudentRegistrationRequest request);

    // Delete
    void deleteStudent(Long id);

    // Additional methods
    long countStudents();
    long countStudentsByDepartment(String department);
}
