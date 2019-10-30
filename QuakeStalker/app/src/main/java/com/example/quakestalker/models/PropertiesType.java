package com.example.quakestalker.models;

// PropertiesType.java

import java.io.IOException;

public enum PropertiesType {
    EARTHQUAKE, EXPLOSION;

    public String toValue() {
        switch (this) {
            case EARTHQUAKE: return "earthquake";
            case EXPLOSION: return "explosion";
        }
        return null;
    }

    public static PropertiesType forValue(String value) throws IOException {
        if (value.equals("earthquake")) return EARTHQUAKE;
        if (value.equals("explosion")) return EXPLOSION;
        throw new IOException("Cannot deserialize PropertiesType");
    }
}