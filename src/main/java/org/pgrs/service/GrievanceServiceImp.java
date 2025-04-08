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
import java.util.Optional;

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

    @Override
    public Long countTotalGrievanceByParent(Long parentId){
        return grievanceRepository.countByCreatorId(parentId);
    }

}
