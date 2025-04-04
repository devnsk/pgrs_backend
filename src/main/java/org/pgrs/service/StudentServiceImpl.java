package org.pgrs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.pgrs.dto.StudentRegistrationRequest;
import org.pgrs.dto.response.StudentResponse;
import org.pgrs.entity.Student;
import org.pgrs.entity.User;
import org.pgrs.entity.UserRole;
import org.pgrs.repository.StudentRepository;
import org.pgrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

	@Override
	public StudentResponse registerStudent(StudentRegistrationRequest request) {
		// Validate input
		validateStudentRegistration(request);

		// Create user
		User user = createStudentUser(request);

		// Create student
		Student student = createStudent(request, user);

		// Save student
		student = studentRepository.save(student);

		// Convert and return response
		return convertToStudentResponse(student);
	}

	private void validateStudentRegistration(StudentRegistrationRequest request) {
		// Check if email already exists
		if (userRepository.existsByEmail(request.getStudentEmail())) {
			throw new RuntimeException("Email already exists");
		}

		// Check if registration number is unique
		if (studentRepository.existsByRegistrationNumber(request.getRegistrationNumber())) {
			throw new RuntimeException("Registration number already exists");
		}
	}

	private User createStudentUser(StudentRegistrationRequest request) {
		User user = new User();
		user.setEmail(request.getStudentEmail());
//		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPassword(request.getPassword());
		user.setRole(UserRole.STUDENT);
		user.setEnabled(false);
		return userRepository.save(user);
	}

	private Student createStudent(StudentRegistrationRequest request, User user) {
		Student student = new Student();
		student.setUser(user);
		student.setFirstName(request.getStudentFirstName());
		student.setLastName(request.getStudentLastName());
		student.setRegistrationNumber(request.getRegistrationNumber());
		student.setDepartment(request.getDepartment());
		student.setBatch(request.getBatch());
		student.setAddress(request.getAddress());
		return student;
	}

	@Override
	public Optional<StudentResponse> getStudentById(Long id) {
		return studentRepository.findById(id).map(this::convertToStudentResponse);
	}

	@Override
	public Optional<StudentResponse> getStudentByRegistrationNumber(String registrationNumber) {
		return studentRepository.findByRegistrationNumber(registrationNumber).map(this::convertToStudentResponse);
	}

	@Override
	public List<StudentResponse> getAllStudents() {
		return studentRepository.findAll().stream().map(this::convertToStudentResponse).collect(Collectors.toList());
	}

	@Override
	public List<StudentResponse> getStudentsByDepartment(String department) {
		return studentRepository.findByDepartment(department).stream().map(this::convertToStudentResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<StudentResponse> getStudentsByBatch(String batch) {
		return studentRepository.findByBatch(batch).stream().map(this::convertToStudentResponse)
				.collect(Collectors.toList());
	}

	@Override
	public StudentResponse updateStudent(Long id, StudentRegistrationRequest request) {
		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found"));

		// Update student details
		existingStudent.setFirstName(request.getStudentFirstName());
		existingStudent.setLastName(request.getStudentLastName());
		existingStudent.setDepartment(request.getDepartment());
		existingStudent.setBatch(request.getBatch());
		existingStudent.setAddress(request.getAddress());

		// Save and return
		return convertToStudentResponse(studentRepository.save(existingStudent));
	}

	@Override
	public void deleteStudent(Long id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

		// Delete associated user
		userRepository.delete(student.getUser());

		// Delete student
		studentRepository.delete(student);
	}

	@Override
	public long countStudents() {
		return studentRepository.count();
	}

	@Override
	public long countStudentsByDepartment(String department) {
		return studentRepository.countByDepartment(department);
	}

	// Utility method to convert Student to StudentResponse
	private StudentResponse convertToStudentResponse(Student student) {
		StudentResponse response = new StudentResponse();
//		response.setId(student.getId());
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setEmail(student.getUser().getEmail());
		response.setRegistrationNumber(student.getRegistrationNumber());
		response.setDepartment(student.getDepartment());
		response.setBatch(student.getBatch());
		response.setAddress(student.getAddress());
		return response;
	}
}
