package com.cinema.auth.dto;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    
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
}
