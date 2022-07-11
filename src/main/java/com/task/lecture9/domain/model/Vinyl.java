package com.task.lecture9.domain.model;

import java.time.Year;

public class Vinyl {
    private Integer id;
    private String title;
    private String artist;
    private String label;
    private Year year;

    public Vinyl(int id, String title, String artist, String label, Year year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.year = year;
    }
    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }


}