package ru.rsatu.rwa.pojo.dto;

public enum RoleDto {
    ADMIN("ADMIN"),
    USER("USER"),
    TEACHER("TEACHER");
    private final String role;

    RoleDto(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
