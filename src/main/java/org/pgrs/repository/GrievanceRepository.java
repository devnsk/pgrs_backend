package org.pgrs.repository;

import org.pgrs.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    @Query("SELECT g FROM Grievance g WHERE g.status = 'PENDING' AND g.creator.id = :creatorId")
    List<Grievance> findPendingByCreatorId(@Param("creatorId") Long creatorId);

    @Query("SELECT g FROM Grievance g WHERE g.status = 'IN_PROGRESS' AND g.creator.id = :creatorId")
    List<Grievance> findInProgressByCreatorId(@Param("creatorId") Long creatorId);
}
