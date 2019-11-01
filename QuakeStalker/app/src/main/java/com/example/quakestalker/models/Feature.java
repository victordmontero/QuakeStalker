package com.example.quakestalker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Feature implements Parcelable {
    private String type;
    private Properties properties;
    private Geometry geometry;
    private String id;

    protected Feature(Parcel in) {
        type = in.readString();
        id = in.readString();
    }

    public static final Creator<Feature> CREATOR = new Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel in) {
            return new Feature(in);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public Properties getProperties() { return properties; }
    public void setProperties(Properties value) { this.properties = value; }

    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry value) { this.geometry = value; }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(id);
    }
}
