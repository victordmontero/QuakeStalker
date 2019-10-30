// Quake.java

package com.example.quakestalker.models;

import java.util.*;

import javax.inject.Named;

public class Quake {

    private String type;
    private Metadata metadata;
    private List<Feature> features;
    private List<Double> bbox;

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata value) { this.metadata = value; }

    public List<Feature> getFeatures() { return features; }
    public void setFeatures(List<Feature> value) { this.features = value; }

    public List<Double> getBbox() { return bbox; }
    public void setBbox(List<Double> value) { this.bbox = value; }
}


