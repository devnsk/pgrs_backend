package org.pgrs.controller;

import java.util.List;

import org.pgrs.dto.StudentRegistrationRequest;
import org.pgrs.dto.response.RegistrationResponse;
import org.pgrs.dto.response.StudentResponse;
import org.pgrs.service.RegistrationImpl;
import org.pgrs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RegistrationImpl registerService;

    // Register a new student
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> registerStudent(
        @Valid @RequestBody StudentRegistrationRequest request
    ) throws Exception {
        RegistrationResponse response = registerService.registerStudentWithParent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Get student by registration number
    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<StudentResponse> getStudentByRegistrationNumber(
        @PathVariable String registrationNumber
    ) {
        return studentService.getStudentByRegistrationNumber(registrationNumber)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Get all students
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get students by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponse>> getStudentsByDepartment(
        @PathVariable String department
    ) {
        List<StudentResponse> students = studentService.getStudentsByDepartment(department);
        return ResponseEntity.ok(students);
    }

    // Get students by batch
    @GetMapping("/batch/{batch}")
    public ResponseEntity<List<StudentResponse>> getStudentsByBatch(
        @PathVariable String batch
    ) {
        List<StudentResponse> students = studentService.getStudentsByBatch(batch);
        return ResponseEntity.ok(students);
    }

    // Update student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
        @PathVariable Long id,
        @Valid @RequestBody StudentRegistrationRequest request
    ) {
        StudentResponse updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // Count students
    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        long count = studentService.countStudents();
        return ResponseEntity.ok(count);
    }

    // Count students by department
    @GetMapping("/count/{department}")
    public ResponseEntity<Long> countStudentsByDepartment(
        @PathVariable String department
    ) {
        long count = studentService.countStudentsByDepartment(department);
        return ResponseEntity.ok(count);
    }
}
