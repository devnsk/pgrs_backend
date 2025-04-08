package org.pgrs.controller;


import lombok.RequiredArgsConstructor;
import org.pgrs.service.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/parent/dashboard")
@RequiredArgsConstructor
public class ParentDashboardController {

    private final GrievanceService grievanceService;

    @GetMapping("/complaints-count/{parentId}")
    public ResponseEntity<Map<String, Long>> getTotalComplaintsByParent(@PathVariable Long parentId){
        long totalcount = grievanceService.countTotalGrievanceByParent(parentId);
        return ResponseEntity.ok(Map.of("Total Complaints Filed By Perticular Parent", totalcount));
    }
}


