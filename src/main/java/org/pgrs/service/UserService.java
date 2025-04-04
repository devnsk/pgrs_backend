package org.pgrs.service;

import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByEmailAndRole(String email, UserRole role);
    long countByRole(UserRole role);
    Optional<User> findByRoleAndEnabled(UserRole role, boolean enabled);

    User save(User user); //for save the data

    List<User> findAllByRole(UserRole role);


}
