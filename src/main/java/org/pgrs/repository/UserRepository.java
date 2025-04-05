package org.pgrs.repository;

import java.util.List;
import java.util.Optional;

import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find user by email and role
    Optional<User> findByEmailAndRole(String email, UserRole role);

    // Count users by role
    long countByRole(UserRole role);

    // Find enabled users by role
    Optional<User> findByRoleAndEnabled(UserRole role, boolean enabled);
    //Find user by roles
    List<User> findAllByRole(UserRole role);
}