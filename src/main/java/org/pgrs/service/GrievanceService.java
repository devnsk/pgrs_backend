package org.pgrs.service;

import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.dto.response.GrievanceResponse;
import org.pgrs.entity.Grievance;
import org.pgrs.entity.GrievanceType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GrievanceService {
    GrievanceResponse createGrievance(GrievanceCreationRequest grievanceCreationRequest);

    GrievanceType createGrievanceType(GrievanceType grievanceType);

    // Retrieves all grievances in "PENDING" status created by the specified user.
    List<GrievanceResponse> getPendingGrievancesByUserId(Long creatorId);

    // Retrieves all grievances in "IN_PROGRESS" status created by the specified user.
    List<GrievanceResponse> getInProgressGrievancesByUserId(Long creatorId);

}
