package org.pgrs.entity;

import java.time.LocalDateTime;

import org.yaml.snakeyaml.comments.CommentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grievance_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrievanceComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grievance_id", nullable = false)
    private Grievance grievance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User commentedBy;

    @Column(columnDefinition = "TEXT")
    private String content;


    @Column(nullable = false)
    private LocalDateTime commentedAt;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Grievance getGrievance() {
		return grievance;
	}


	public void setGrievance(Grievance grievance) {
		this.grievance = grievance;
	}


	public User getCommentedBy() {
		return commentedBy;
	}


	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getCommentedAt() {
		return commentedAt;
	}


	public void setCommentedAt(LocalDateTime commentedAt) {
		this.commentedAt = commentedAt;
	}


    // Constructors, getters, and setters
}