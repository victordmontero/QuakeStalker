// Quake.java

package com.example.quakestalker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

import javax.inject.Named;

public class Quake implements Parcelable {

    private String type;
    private Metadata metadata;
    private List<Feature> features;
    private List<Double> bbox;

    protected Quake(Parcel in) {
        type = in.readString();
        metadata = in.readParcelable(Metadata.class.getClassLoader());
        features = in.createTypedArrayList(Feature.CREATOR);
    }

    public static final Creator<Quake> CREATOR = new Creator<Quake>() {
        @Override
        public Quake createFromParcel(Parcel in) {
            return new Quake(in);
        }

        @Override
        public Quake[] newArray(int size) {
            return new Quake[size];
        }
    };

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata value) { this.metadata = value; }

    public List<Feature> getFeatures() { return features; }
    public void setFeatures(List<Feature> value) { this.features = value; }

    public List<Double> getBbox() { return bbox; }
    public void setBbox(List<Double> value) { this.bbox = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeParcelable(metadata, flags);
        dest.writeTypedList(features);
    }
}


