package com.str.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quote_request")
public class QuoteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceType;
    private String brand;
    private String model;
    private String issue;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    private String status;
}