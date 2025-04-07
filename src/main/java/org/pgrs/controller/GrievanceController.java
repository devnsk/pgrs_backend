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
//    @GetMapping("/pending/{userId}")
//    public ResponseEntity<List<GrievanceResponse>> getPending(@PathVariable Long userId) {
//        return ResponseEntity.ok(grievanceService.getPendingGrievancesByUserId(userId));
//    }
//
//    @GetMapping("/in-progress/{userId}")
//    public ResponseEntity<List<GrievanceResponse>> getInProgress(@PathVariable Long userId) {
//        return ResponseEntity.ok(grievanceService.getInProgressGrievancesByUserId(userId));
//    }

    @GetMapping("/pending/{userId}")
    public ResponseEntity<?> getPending(@PathVariable Long userId) {
        List<GrievanceResponse> grievances = grievanceService.getPendingGrievancesByUserId(userId);

        if (grievances.isEmpty()) {
            return ResponseEntity.ok().body(
                    Map.of("message", "No pending grievances found for this user.")
            );
        }

        return ResponseEntity.ok(grievances);
    }

    @GetMapping("/in-progress/{userId}")
    public ResponseEntity<?> getInProgress(@PathVariable Long userId) {
        List<GrievanceResponse> grievances = grievanceService.getInProgressGrievancesByUserId(userId);

        if (grievances.isEmpty()) {
            return ResponseEntity.ok().body(
                    Map.of("message", "No grievances in progress for this user.")
            );
        }

        return ResponseEntity.ok(grievances);
    }


}
