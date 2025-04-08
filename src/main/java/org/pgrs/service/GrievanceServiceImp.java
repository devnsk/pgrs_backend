package org.pgrs.service;

import jakarta.transaction.Transactional;
import org.pgrs.Exception.DuplicateGrievanceException;
import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.dto.response.GrievanceResponse;
import org.pgrs.entity.Grievance;
import org.pgrs.entity.GrievanceStatus;
import org.pgrs.entity.GrievanceType;
import org.pgrs.entity.User;
import org.pgrs.repository.GreTypeRepository;
import org.pgrs.repository.GrievanceRepository;
import org.pgrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GrievanceServiceImp implements GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private GreTypeRepository greTypeRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public GrievanceResponse createGrievance(GrievanceCreationRequest grievanceCreationRequest) {


        try {
            // Finding the Grievance Type by ID
            GrievanceType byId = greTypeRepository.getById(grievanceCreationRequest.getCategoryId());

            // Finding the User Details by ID
            User user = userRepository.getById(grievanceCreationRequest.getCreatedByUserId());

            // Set the all values from Request Class
            Grievance grievance = new Grievance();
            grievance.setType(byId);
            grievance.setCreatedAt(grievanceCreationRequest.getDateOfIncident().atStartOfDay());
            grievance.setCreator(user);
            grievance.setDescription(grievanceCreationRequest.getDescriptionOfIssue());
            grievance.setStatus(GrievanceStatus.PENDING);

            // Saved the Grievance
            Grievance saved = grievanceRepository.save(grievance);

            return new GrievanceResponse(
                    saved.getId(),
                    saved.getCreator().getEmail(),
                    "Grievance Created Successfully"
            );

        } catch (Exception e) {

            throw new RuntimeException("Grievance process failed: " + e.getMessage());
        }


    }

    @Override
    public GrievanceType createGrievanceType(GrievanceType grievanceType) {
        // Check if a grievance type with the same name already exists
        Optional<GrievanceType> existingType = greTypeRepository.findByNameIgnoreCase(grievanceType.getName());

        if (existingType.isPresent()) {
            throw new DuplicateGrievanceException("Grievance type with this name already exists.");
        }

        // Save and return
        return greTypeRepository.save(grievanceType);
    }

    /**
     * Fetches all grievances created by the given user where the status is "PENDING".
     * It then transforms each grievance into a simplified response format.
     *
     * @param creatorId The ID of the user who raised the grievances.
     * @return List of GrievanceResponse containing grievance ID, creator email, and status.
     */
    @Override
    public List<GrievanceResponse> getPendingGrievancesByUserId(Long creatorId) {
        return grievanceRepository.findPendingByCreatorId(creatorId).stream()
                .map(g -> new GrievanceResponse(g.getId(), g.getCreator().getEmail(), "Pending"))
                .collect(Collectors.toList());
    }

    /**
     * Fetches all grievances created by the given user where the status is "IN_PROGRESS".
     * Each grievance is mapped into a simplified response structure.
     *
     * @param creatorId The ID of the user who raised the grievances.
     * @return List of GrievanceResponse containing grievance ID, creator email, and status.
     */
    @Override
    public List<GrievanceResponse> getInProgressGrievancesByUserId(Long creatorId) {
        return grievanceRepository.findInProgressByCreatorId(creatorId).stream()
                .map(g -> new GrievanceResponse(g.getId(), g.getCreator().getEmail(), "In Progress"))
                .collect(Collectors.toList());
    }

}
