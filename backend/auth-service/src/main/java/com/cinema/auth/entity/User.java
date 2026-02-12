package com.cinema.auth.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String fullName;
    
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public enum Role {
        USER, ADMIN
    }
    
    public User() {}
    
    public User(Long id, String email, String password, String fullName, String phone, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.role = role;
        this.createdAt = createdAt;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public Role getRole() {
        return role;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
