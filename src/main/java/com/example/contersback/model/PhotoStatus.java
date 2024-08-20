package com.example.contersback.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PhotoStatus {
    UNCHECKED("UNCHECKED"),
    CHECKED("CHECKED"),
    DOUBT("DOUBT"),
    INCORRECT("INCORRECT");

    private final String value;

    PhotoStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PhotoStatus fromValue(String value) {
        for (PhotoStatus status : PhotoStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
