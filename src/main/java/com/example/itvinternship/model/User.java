package com.example.itvinternship.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    private String role;

    private String resetToken;

    @Enumerated(EnumType.STRING)
    private Status status = Status.active;

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    
    public static enum Status {
        active, inactive
    }
}