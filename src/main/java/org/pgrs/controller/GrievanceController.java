package org.pgrs.controller;

import org.pgrs.entity.Grievance;
import org.pgrs.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    // Create Grievance
    @PostMapping
    public Grievance createGrievance(@RequestBody Grievance grievance) {
        return grievanceService.createGrievance(grievance);
    }

    // Get All Grievances
    @GetMapping
    public List<Grievance> getAllGrievances() {
        return grievanceService.getAllGrievances();
    }

    // Get Grievance by ID
    @GetMapping("/{id}")
    public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long id) {
        Optional<Grievance> grievance = grievanceService.getGrievanceById(id);
        return grievance.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Grievance
    @PutMapping("/{id}")
    public ResponseEntity<Grievance> updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {
        try {
            return ResponseEntity.ok(grievanceService.updateGrievance(id, grievance));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Grievance
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrievance(@PathVariable Long id) {
        grievanceService.deleteGrievance(id);
        return ResponseEntity.noContent().build();
    }
}
