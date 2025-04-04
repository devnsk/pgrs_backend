package org.pgrs.controller;

import jakarta.validation.Valid;
import org.pgrs.dto.StudentRegistrationRequest;
import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;
import org.pgrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("User with this email already exists.");
        }

        user.setEnabled(true);
        userService.save(user);
        return ResponseEntity.ok("User created successfully by Super Admin.");
    }



    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        return ResponseEntity.ok(userService.existsByEmail(email));
    }

    @GetMapping("/by-email-role")
    public ResponseEntity<User> getUserByEmailAndRole(@RequestParam String email,
                                                      @RequestParam UserRole role) {
        Optional<User> user = userService.findByEmailAndRole(email, role);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count-by-role/{role}")
    public ResponseEntity<Long> countUsersByRole(@PathVariable UserRole role) {
        return ResponseEntity.ok(userService.countByRole(role));
    }

    @GetMapping("/list-by-role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable UserRole role) {
        List<User> users = userService.findAllByRole(role);
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/enabled")
    public ResponseEntity<User> getEnabledUserByRole(@RequestParam UserRole role,
                                                     @RequestParam boolean enabled) {
        Optional<User> user = userService.findByRoleAndEnabled(role, enabled);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//Update the enable option
    @PutMapping("/update-enabled")
    public ResponseEntity<String> updateUserEnabledStatus(@RequestParam String email, @RequestParam boolean enabled) {
        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEnabled(enabled);
            userService.save(user);
            return ResponseEntity.ok("User 'enabled' status updated successfully.");
        }
        return ResponseEntity.notFound().build();
    }


}
