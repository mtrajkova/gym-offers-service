package com.bachelor.microservice1.model;

public class UserPrincipal {
    private Integer id;
    private String username;
    private boolean isAdmin;
    private boolean isCreator;

    public UserPrincipal() {
    }

    public UserPrincipal(Integer id, String username, boolean isAdmin, boolean isCreator) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
        this.isCreator = isCreator;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isCreator() {
        return isCreator;
    }

    public void setCreator(boolean creator) {
        isCreator = creator;
    }
}
