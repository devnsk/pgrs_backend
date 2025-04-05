package org.pgrs.service;

import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;
import org.pgrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndRole(String email, UserRole role) {
        return userRepository.findByEmailAndRole(email, role);
    }

    @Override
    public long countByRole(UserRole role) {
        return userRepository.countByRole(role);
    }

    @Override
    public Optional<User> findByRoleAndEnabled(UserRole role, boolean enabled) {
        return userRepository.findByRoleAndEnabled(role, enabled);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }



}
