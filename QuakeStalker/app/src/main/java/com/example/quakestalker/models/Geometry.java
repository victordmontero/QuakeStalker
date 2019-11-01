package com.example.quakestalker.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Geometry implements Parcelable {
    private String type;
    private List<Double> coordinates;

    protected Geometry(Parcel in) {
        type = in.readString();
    }

    public static final Creator<Geometry> CREATOR = new Creator<Geometry>() {
        @Override
        public Geometry createFromParcel(Parcel in) {
            return new Geometry(in);
        }

        @Override
        public Geometry[] newArray(int size) {
            return new Geometry[size];
        }
    };

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public List<Double> getCoordinates() { return coordinates; }
    public void setCoordinates(List<Double> value) { this.coordinates = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }
}
