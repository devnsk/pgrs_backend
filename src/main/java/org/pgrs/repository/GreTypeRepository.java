package org.pgrs.repository;

import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.entity.GrievanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreTypeRepository extends JpaRepository<GrievanceType,Long> {

    Optional<GrievanceType> findByNameIgnoreCase(String name);
}
