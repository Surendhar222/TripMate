package com.tripmate.data.dto;

import java.util.Objects;

public class Credential {
    private String userId;
    private String email;
    private String password;
    private boolean active;

    public Credential() {}

    public Credential(String userId, String email, String password, boolean active) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return active == that.active && Objects.equals(userId, that.userId) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, active);
    }
}
