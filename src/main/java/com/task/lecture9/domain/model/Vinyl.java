package com.task.lecture9.domain.model;

public class Vinyl {
    private Integer id;
    private String title;
    private String artist;
    private String label;
    private int year;

    public Vinyl() {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.year = year;
    }
    public Integer getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist() {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel() {
        this.label = label;
    }

    public int getYear() {
        return year;
    }

    public void setYear() {
        this.year = year;
    }


}