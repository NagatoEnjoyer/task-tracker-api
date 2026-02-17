package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Spring Boot: "Create a database table for this class!"
public class Task {

    @Id // This tells Spring Boot: "This is the primary key"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This auto-generates the ID (1, 2, 3...)
    private Long id;

    private String title;
    private String description;
    private String status; // e.g., "PENDING" or "COMPLETED"

    // --- Constructors ---
    public Task() {
    }

    public Task(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}