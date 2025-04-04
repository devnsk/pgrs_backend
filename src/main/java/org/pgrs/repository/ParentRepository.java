package org.pgrs.repository;

import java.util.Optional;

import org.pgrs.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    // Find parent by contact number
    Optional<Parent> findByContactNumber(String contactNumber);

    // Find parent by user email
    Optional<Parent> findByUser_Email(String email);

    // Check if contact number exists
    boolean existsByContactNumber(String contactNumber);

    // Find parent by student registration number
    Optional<Parent> findByStudent_RegistrationNumber(String registrationNumber);
}