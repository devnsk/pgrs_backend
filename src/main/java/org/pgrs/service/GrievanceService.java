package org.pgrs.service;

import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.dto.response.GrievanceResponse;
import org.pgrs.entity.Grievance;
import org.pgrs.entity.GrievanceType;
import org.springframework.stereotype.Service;


public interface GrievanceService {
    GrievanceResponse createGrievance(GrievanceCreationRequest grievanceCreationRequest);

    GrievanceType createGrievanceType(GrievanceType grievanceType);

    Long countTotalGrievanceByParent(Long parentId);

}
