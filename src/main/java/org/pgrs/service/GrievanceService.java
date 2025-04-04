package org.pgrs.service;


import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.pgrs.entity.Grievance;
import org.pgrs.repository.GrievanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
@Getter
@Setter
public class GrievanceService {

    private  GrievanceRepository grievanceRepository;



    public Grievance createGrievance(Grievance grievance){
        grievance.setCreatedAt(LocalDateTime.now());
        return grievanceRepository.save(grievance);

    }


    public List<Grievance> getAllGrievances(){
        return grievanceRepository.findAll();
    }

    public Optional<Grievance> getGrievancebyId(Long id){
        return grievanceRepository.findById(id);

    }


    public Grievance updateGrievance(Long id, Grievance updatedGrievance){
        return grievanceRepository.findById(id).map(grievance -> {
            grievance.setTitle(updatedGrievance.getTitle());
            grievance.setDescription(updatedGrievance.getDescription());
            grievance.setStatus(updatedGrievance.getStatus());
            grievance.setType(updatedGrievance.getType());
            grievance.setAssignedTo(updatedGrievance.getAssignedTo());
            grievance.setUpdatedAt(LocalDateTime.now());
            return grievanceRepository.save(grievance);
        }).orElseThrow(()->new RuntimeException("Grievance not found"));
    }

    // Delete Grievance
    public void deleteGrievance(Long id){
        grievanceRepository.deleteById(id);
    }
}

