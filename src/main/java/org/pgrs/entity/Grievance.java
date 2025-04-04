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
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Grievance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User creator;

    @ManyToOne
    private GrievanceType Type;

    private String Title;
    private String Description;

    @Enumerated(EnumType.STRING)
    private GrievanceStatus Status;

    @ManyToOne
    private Employee AssignedTo;

    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;

    @OneToMany(mappedBy = "grievance")
    private List<GrievanceAttachment> attachments;

    @OneToMany(mappedBy = "grievance")
    private List<GrievanceComment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public GrievanceType getType() {
        return Type;
    }

    public void setType(GrievanceType type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public GrievanceStatus getStatus() {
        return Status;
    }

    public void setStatus(GrievanceStatus status) {
        Status = status;
    }

    public Employee getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        AssignedTo = assignedTo;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }

    public List<GrievanceAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<GrievanceAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<GrievanceComment> getComments() {
        return comments;
    }

    public void setComments(List<GrievanceComment> comments) {
        this.comments = comments;
    }
}
