package org.pgrs.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grievance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User creator;

    @ManyToOne
    private GrievanceType type;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private GrievanceStatus status;

    @ManyToOne
    private Employee assignedTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "grievance")
    private List<GrievanceAttachment> attachments;

    @OneToMany(mappedBy = "grievance")
    private List<GrievanceComment> comments;


    
}