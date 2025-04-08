package org.pgrs.controller;

import jakarta.validation.Valid;
import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.dto.response.GrievanceResponse;
import org.pgrs.entity.GrievanceType;
import org.pgrs.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grievance")
public class GrievanceController {

    private final GrievanceService grievanceService;

    @Autowired
    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    //Creation of Grievance
    @PostMapping("/register")
    public ResponseEntity<GrievanceResponse> registerGrievance(@Valid @RequestBody GrievanceCreationRequest request)
    {
        GrievanceResponse response = grievanceService.createGrievance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Creation of Grievance Type
    @PostMapping("/create-type")
    public ResponseEntity<?> createGrievanceType(@Valid @RequestBody GrievanceType grievanceType) {
        GrievanceType savedType = grievanceService.createGrievanceType(grievanceType);
        return new ResponseEntity<>(savedType, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all grievances with status "PENDING" for a specific user.
     *
     * @param userId ID of the user whose pending grievances are to be fetched.
     * @return A list of GrievanceResponse if found, or a message indicating no data.
     */
    @GetMapping("/pending/{userId}")
    public ResponseEntity<?> getPending(@PathVariable Long userId) {
        // Fetch pending grievances for the given user from the service layer
        List<GrievanceResponse> grievances = grievanceService.getPendingGrievancesByUserId(userId);

        // If the list is empty, return a message indicating no pending grievances
        if (grievances.isEmpty()) {
            return ResponseEntity.ok().body(
                    Map.of("message", "No pending grievances found for this user.")
            );
        }

        // Otherwise, return the list of pending grievances
        return ResponseEntity.ok(grievances);
    }

    /**
     * Endpoint to retrieve all grievances with status "IN_PROGRESS" for a specific user.
     *
     * @param userId ID of the user whose in-progress grievances are to be fetched.
     * @return A list of GrievanceResponse if found, or a message indicating no data.
     */
    @GetMapping("/in-progress/{userId}")
    public ResponseEntity<?> getInProgress(@PathVariable Long userId) {
        // Fetch in-progress grievances for the given user from the service layer
        List<GrievanceResponse> grievances = grievanceService.getInProgressGrievancesByUserId(userId);

        // If the list is empty, return a message indicating no grievances in progress
        if (grievances.isEmpty()) {
            return ResponseEntity.ok().body(
                    Map.of("message", "No grievances in progress for this user.")
            );
        }

        // Otherwise, return the list of in-progress grievances
        return ResponseEntity.ok(grievances);
    }
}
