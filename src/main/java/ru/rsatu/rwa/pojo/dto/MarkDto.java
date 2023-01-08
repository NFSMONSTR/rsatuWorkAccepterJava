package ru.rsatu.rwa.pojo.dto;

public enum MarkDto {
    EXCELLENT("EXCELLENT"),
    GOOD("GOOD"),
    FAIR("FAIR"),
    BAD("BAD");
    private final String mark;

    MarkDto(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return this.mark;
    }
}

