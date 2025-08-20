package com.example.crm.entity;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    public enum Status {
        PENDING,
        COMPLETED
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private LocalDate dueDate;

    @ManyToOne @JoinColumn(name="client_id")
    private Client client;
    @ManyToOne @JoinColumn(name="assignee_id")
    private User assignee;

    private Instant createdAt = Instant.now();

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public void setDueDate(Date date) {
        this.dueDate = date.toLocalDate();
    }
}