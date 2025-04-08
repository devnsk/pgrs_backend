package org.pgrs.repository;

import org.pgrs.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {

    @Query("SELECT COUNT(g) FROM Grievance g WHERE g.creator.id = :parentId")
    Long countByCreatorId(Long parentId);


}
