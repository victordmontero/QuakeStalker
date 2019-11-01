package com.example.quakestalker.models;

// Metadata.java

import android.os.Parcel;
import android.os.Parcelable;

public class Metadata implements Parcelable {
    private Long generated;
    private String url;
    private String title;
    private Long status;
    private String api;
    private Long count;

    protected Metadata(Parcel in) {
        if (in.readByte() == 0) {
            generated = null;
        } else {
            generated = in.readLong();
        }
        url = in.readString();
        title = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readLong();
        }
        api = in.readString();
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readLong();
        }
    }

    public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel in) {
            return new Metadata(in);
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };

    public Long getGenerated() { return generated; }
    public void setGenerated(Long value) { this.generated = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public Long getStatus() { return status; }
    public void setStatus(Long value) { this.status = value; }

    public String getAPI() { return api; }
    public void setAPI(String value) { this.api = value; }

    public Long getCount() { return count; }
    public void setCount(Long value) { this.count = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (generated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(generated);
        }
        dest.writeString(url);
        dest.writeString(title);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(status);
        }
        dest.writeString(api);
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(count);
        }
    }
}
