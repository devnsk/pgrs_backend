package org.pgrs.service;

import java.util.UUID;

import org.pgrs.dto.StudentRegistrationRequest;
import org.pgrs.dto.response.RegistrationResponse;

import org.pgrs.entity.Parent;
import org.pgrs.entity.Student;
import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;
import org.pgrs.repository.ParentRepository;
import org.pgrs.repository.StudentRepository;
import org.pgrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class RegistrationImpl {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParentRepository parentRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

  
    public RegistrationResponse registerStudentWithParent(StudentRegistrationRequest request) throws Exception {
        // 1. Validate Registration Request
//        validateRegistrationRequest(request);

        try {
            // 2. Create User for Student
            User studentUser = createStudentUser(request);

            // 3. Create User for Parent
            User parentUser = createParentUser(request);

            // 4. Create Parent Entity
            Parent parent = createParentEntity(request, parentUser);

            // 5. Create Student Entity
            Student student = createStudentEntity(request, studentUser, parent);

            // 6. Save Entities
            // Save parent first to ensure parent_id is available for student
            parent = parentRepository.save(parent);
            
            // Update student with saved parent
            student.setParent(parent);
            studentRepository.save(student);

            // 7. Create Registration Response
            return new RegistrationResponse(
                student.getFirstName(),
                student.getParent().getFirstName(),
                "Student and Parent registered successfully"
            );

        } catch (Exception e) {
            // Log the error
//            Logger.error("Registration failed", e);
            throw new Exception("Registration process failed: " + e.getMessage());
        }
    }

    private void validateRegistrationRequest(StudentRegistrationRequest request) {
	// TODO Auto-generated method stub
	
}
/*
 * Working perfectly
 */
	private User createStudentUser(StudentRegistrationRequest request) {
        User studentUser = new User();
        studentUser.setEmail(request.getStudentEmail());
//        studentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        studentUser.setPassword(request.getPassword());
        studentUser.setRole(UserRole.STUDENT);
        studentUser.setEnabled(false); // Require email verification
        return userRepository.save(studentUser);
    }

    private User createParentUser(StudentRegistrationRequest request) {
        User parentUser = new User();
        parentUser.setEmail(request.getParentEmail());
        // Generate a temporary password for parent
        String tempPassword = generateTemporaryPassword();
//        parentUser.setPassword(passwordEncoder.encode(tempPassword));
        parentUser.setPassword(tempPassword);
        parentUser.setRole(UserRole.PARENT);
        parentUser.setEnabled(false);
        return userRepository.save(parentUser);
    }

    private Parent createParentEntity(StudentRegistrationRequest request, User parentUser) {
        Parent parent = new Parent();
        parent.setUser(parentUser);
        parent.setFirstName(request.getParentFirstName());
        parent.setLastName(request.getParentLastName());
        parent.setContactNumber(request.getParentContactNumber());
        parent.setRelationship(request.getRelationship());
        return parent;
    }

    private Student createStudentEntity(
        StudentRegistrationRequest request, 
        User studentUser, 
        Parent parent
    ) {
        Student student = new Student();
        student.setUser(studentUser);
        student.setFirstName(request.getStudentFirstName());
        student.setLastName(request.getStudentLastName());
        student.setRegistrationNumber(request.getRegistrationNumber());
        student.setDepartment(request.getDepartment());
        student.setBatch(request.getBatch());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setAddress(request.getAddress());
        student.setParent(parent);
        return student;
    }

    // Existing validation and other methods...

    private String generateTemporaryPassword() {
        // Generate a secure temporary password
        return UUID.randomUUID().toString().substring(0, 12);
    }
}
