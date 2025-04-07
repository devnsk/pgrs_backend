package org.pgrs.repository;

import org.pgrs.entity.GrievanceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreAttachmentRepository extends JpaRepository<GrievanceAttachment,Long> {
}
