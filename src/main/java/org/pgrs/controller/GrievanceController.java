package org.pgrs.controller;

import jakarta.validation.Valid;
import org.pgrs.dto.GrievanceCreationRequest;
import org.pgrs.dto.response.GrievanceResponse;
import org.pgrs.entity.GrievanceType;
import org.pgrs.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
