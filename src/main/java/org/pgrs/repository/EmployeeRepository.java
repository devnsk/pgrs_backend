package org.pgrs.repository;

import org.pgrs.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Employee entity.
 *
 * This interface provides CRUD operations and query capabilities
 * for Employee entities by extending JpaRepository.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom query methods here if needed.
    // For example: List<Employee> findByDepartmentId(Long departmentId);
}
