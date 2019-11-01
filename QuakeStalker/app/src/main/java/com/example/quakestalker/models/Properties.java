package com.example.quakestalker.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Properties implements Parcelable {
    private Double mag;
    private String place;
    private Long time;
    private Long updated;
    private Long tz;
    private String url;
    private String detail;
    private Long felt;
    private Double cdi;
    private Double mmi;
    private String alert;
    private String status;
    private Long tsunami;
    private Long sig;
    private String net;
    private String code;
    private String ids;
    private String sources;
    private String types;
    private Long nst;
    private Double dmin;
    private Double rms;
    private Double gap;
    private String magType;
    private PropertiesType type;
    private String title;

    protected Properties(Parcel in) {
        if (in.readByte() == 0) {
            mag = null;
        } else {
            mag = in.readDouble();
        }
        place = in.readString();
        if (in.readByte() == 0) {
            time = null;
        } else {
            time = in.readLong();
        }
        if (in.readByte() == 0) {
            updated = null;
        } else {
            updated = in.readLong();
        }
        if (in.readByte() == 0) {
            tz = null;
        } else {
            tz = in.readLong();
        }
        url = in.readString();
        detail = in.readString();
        if (in.readByte() == 0) {
            felt = null;
        } else {
            felt = in.readLong();
        }
        if (in.readByte() == 0) {
            cdi = null;
        } else {
            cdi = in.readDouble();
        }
        if (in.readByte() == 0) {
            mmi = null;
        } else {
            mmi = in.readDouble();
        }
        alert = in.readString();
        status = in.readString();
        if (in.readByte() == 0) {
            tsunami = null;
        } else {
            tsunami = in.readLong();
        }
        if (in.readByte() == 0) {
            sig = null;
        } else {
            sig = in.readLong();
        }
        net = in.readString();
        code = in.readString();
        ids = in.readString();
        sources = in.readString();
        types = in.readString();
        if (in.readByte() == 0) {
            nst = null;
        } else {
            nst = in.readLong();
        }
        if (in.readByte() == 0) {
            dmin = null;
        } else {
            dmin = in.readDouble();
        }
        if (in.readByte() == 0) {
            rms = null;
        } else {
            rms = in.readDouble();
        }
        if (in.readByte() == 0) {
            gap = null;
        } else {
            gap = in.readDouble();
        }
        magType = in.readString();
        title = in.readString();
    }

    public static final Creator<Properties> CREATOR = new Creator<Properties>() {
        @Override
        public Properties createFromParcel(Parcel in) {
            return new Properties(in);
        }

        @Override
        public Properties[] newArray(int size) {
            return new Properties[size];
        }
    };

    public Double getMag() { return mag; }
    public void setMag(Double value) { this.mag = value; }

    public String getPlace() { return place; }
    public void setPlace(String value) { this.place = value; }

    public Long getTime() { return time; }
    public void setTime(Long value) { this.time = value; }

    public Long getUpdated() { return updated; }
    public void setUpdated(Long value) { this.updated = value; }

    public Long getTz() { return tz; }
    public void setTz(Long value) { this.tz = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getDetail() { return detail; }
    public void setDetail(String value) { this.detail = value; }

    public Long getFelt() { return felt; }
    public void setFelt(Long value) { this.felt = value; }

    public Double getCdi() { return cdi; }
    public void setCdi(Double value) { this.cdi = value; }

    public Double getMMI() { return mmi; }
    public void setMMI(Double value) { this.mmi = value; }

    public String getAlert() { return alert; }
    public void setAlert(String value) { this.alert = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public Long getTsunami() { return tsunami; }
    public void setTsunami(Long value) { this.tsunami = value; }

    public Long getSig() { return sig; }
    public void setSig(Long value) { this.sig = value; }

    public String getNet() { return net; }
    public void setNet(String value) { this.net = value; }

    public String getCode() { return code; }
    public void setCode(String value) { this.code = value; }

    public String getIDS() { return ids; }
    public void setIDS(String value) { this.ids = value; }

    public String getSources() { return sources; }
    public void setSources(String value) { this.sources = value; }

    public String getTypes() { return types; }
    public void setTypes(String value) { this.types = value; }

    public Long getNst() { return nst; }
    public void setNst(Long value) { this.nst = value; }

    public Double getDmin() { return dmin; }
    public void setDmin(Double value) { this.dmin = value; }

    public Double getRMS() { return rms; }
    public void setRMS(Double value) { this.rms = value; }

    public Double getGap() { return gap; }
    public void setGap(Double value) { this.gap = value; }

    public String getMagType() { return magType; }
    public void setMagType(String value) { this.magType = value; }

    public PropertiesType getType() { return type; }
    public void setType(PropertiesType value) { this.type = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mag == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mag);
        }
        dest.writeString(place);
        if (time == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(time);
        }
        if (updated == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(updated);
        }
        if (tz == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(tz);
        }
        dest.writeString(url);
        dest.writeString(detail);
        if (felt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(felt);
        }
        if (cdi == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(cdi);
        }
        if (mmi == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(mmi);
        }
        dest.writeString(alert);
        dest.writeString(status);
        if (tsunami == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(tsunami);
        }
        if (sig == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(sig);
        }
        dest.writeString(net);
        dest.writeString(code);
        dest.writeString(ids);
        dest.writeString(sources);
        dest.writeString(types);
        if (nst == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(nst);
        }
        if (dmin == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(dmin);
        }
        if (rms == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(rms);
        }
        if (gap == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(gap);
        }
        dest.writeString(magType);
        dest.writeString(title);
    }
}