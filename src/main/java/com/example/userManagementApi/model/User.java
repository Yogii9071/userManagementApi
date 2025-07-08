package com.example.userManagementApi.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity // Marks this class as a JPA entity, mapping it to a database table
@Table(name = "users") // Specifies the table name in the database
public class User {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key generation strategy (auto-increment for PostgreSQL)
    private Long id;

    @NotBlank(message = "Name is required") // Validation: Ensures the name field is not null and not empty after trimming
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters") // Validation: Size constraint
    private String name;

    @NotBlank(message = "Email is required") // Validation: Ensures the email field is not null and not empty
    @Email(message = "Email should be valid")// Validation: Ensures the email format is valid
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
    @Column(name="role")
    private Set<String> roles = new HashSet<>();

    @Column(name = "created_at", updatable = false) // 'updatable = false' ensures it's set only once
    @CreationTimestamp
    private LocalDateTime createdAt;

    // New: Update Timestamp
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // Default constructor is required by JPA
    public User(String password, PasswordEncoder passwordEncoder, Set<String> roles) {
        this.password = password;
        this.roles=roles;
    }

    // Constructor with fields (optional, but good for testing/convenience)
    public User(String name, String email, PasswordEncoder passwordEncoder) {
        this.name = name;
        this.email = email;

    }

    public User() {
    }

    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setEmail(String email) {
        this.email = email;
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
