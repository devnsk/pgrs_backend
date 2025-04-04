package org.pgrs.repository;

import java.util.List;
import java.util.Optional;

import org.pgrs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Find student by registration number
    Optional<Student> findByRegistrationNumber(String registrationNumber);

    // Check if registration number exists
    boolean existsByRegistrationNumber(String registrationNumber);

    // Find students by department
    List<Student> findByDepartment(String department);

    // Find students by batch
    List<Student> findByBatch(String batch);

    // Find student by user email
    Optional<Student> findByUser_Email(String email);

    // Count students by department
    long countByDepartment(String department);

    // Find students with parent
    List<Student> findByParentIsNotNull();
}