package org.pgrs.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing an Employee in the system.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	/** Primary key for the Employee entity */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * One-to-One relationship with the User entity.
	 * Each employee is associated with one user account.
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	/** First name of the employee */
	private String firstName;

	/** Last name of the employee */
	private String lastName;

	/**
	 * Many-to-One relationship with the Department entity.
	 * Many employees can belong to the same department.
	 * JsonBackReference is used to prevent circular serialization.
	 */
	@ManyToOne
	@JsonBackReference
	private Department department;

	/**
	 * One-to-Many relationship with Grievance.
	 * An employee may be assigned multiple grievances to resolve.
	 */
	@OneToMany(mappedBy = "assignedTo")
	private List<Grievance> assignedGrievances;

	// Manually defined getters and setters (optional if using Lombok)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Grievance> getAssignedGrievances() {
		return assignedGrievances;
	}

	public void setAssignedGrievances(List<Grievance> assignedGrievances) {
		this.assignedGrievances = assignedGrievances;
	}
}
