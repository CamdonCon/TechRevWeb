package com.str.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class QuoteRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    private String description;
    private String imagePath; // stored relative path or S3 key
    private String status = "NEW"; // NEW | QUOTED | CLOSED
    private Double quotePrice;      // nullable until priced
    private Instant createdAt = Instant.now();
}