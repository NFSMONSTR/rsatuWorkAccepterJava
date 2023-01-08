package ru.rsatu.rwa.pojo.dto;

public enum ConnectionTypeDto {
    WORK("WORK"),
    TRYWORK("TRYWORK"),
    COMMENTWORK("COMMENTWORK");
    private final String connType;

    ConnectionTypeDto(String connType) {
        this.connType = connType;
    }

    @Override
    public String toString() {
        return this.connType;
    }
}
