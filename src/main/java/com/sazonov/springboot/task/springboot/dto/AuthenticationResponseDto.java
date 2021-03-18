package com.sazonov.springboot.task.springboot.dto;


import java.util.List;

public class AuthenticationResponseDto {
    private String token;

    private List<String> roleName;

    public AuthenticationResponseDto() {
    }

    public AuthenticationResponseDto(String token, List<String> authorities) {
        this.token = token;
        this.roleName = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoleName() {
        return roleName;
    }

    public void setRoleName(List<String> roleName) {
        this.roleName = roleName;
    }
}
