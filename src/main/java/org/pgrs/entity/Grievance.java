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
		return type;
	}

	public void setType(GrievanceType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GrievanceStatus getStatus() {
		return status;
	}

	public void setStatus(GrievanceStatus status) {
		this.status = status;
	}

	public Employee getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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