package org.pgrs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrievanceCreationRequest {

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "Description of issue is required")
    private String descriptionOfIssue;

    @NotNull(message = "Date of incident is required")
    private LocalDate dateOfIncident;

    @NotNull(message = "Created By User ID is required")
    private Long createdByUserId;

    private String preferredResolution;

    //This is for Document Upload
//    private List<SupportingDocumentRequest> supportingDocuments;
}