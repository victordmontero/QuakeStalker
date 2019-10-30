package com.example.quakestalker.models;

// Metadata.java

public class Metadata {
    private Long generated;
    private String url;
    private String title;
    private Long status;
    private String api;
    private Long count;

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
}
