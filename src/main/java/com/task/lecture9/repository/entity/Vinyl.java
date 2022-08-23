package com.task.lecture9.repository.entity;

import java.time.Year;
import java.util.Objects;

public class Vinyl {

    private final Integer id;
    private String title;
    private String artist;
    private String label;
    private int release_year;

    public Vinyl(int id, String title, String artist, String label, int release_year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.release_year = release_year;
    }
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String Artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String Label) {
        this.label = label;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Year Year) {
        this.release_year = release_year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vinyl vinyl = (Vinyl) o;
        return release_year == vinyl.release_year && Objects.equals(id, vinyl.id) && Objects.equals(title,
                vinyl.title) && Objects.equals(artist, vinyl.artist) && Objects.equals(label, vinyl.label);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, label, release_year);
    }
    @Override
    public String toString() {
        return "Vinyl{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", label='" + label + '\'' +
                ", release_year=" + release_year +
                '}';
    }
}