package com.task.lecture9.repository.entity;

import java.time.Year;
import java.util.Objects;

public class Vinyl {

    private final Integer id;
    private String title;
    private String artist;
    private String label;
    private int releaseYear;

    public Vinyl(int id, String title, String artist, String label, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.releaseYear = releaseYear;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year Year) {
        this.releaseYear = releaseYear;
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
        return releaseYear == vinyl.releaseYear && Objects.equals(id, vinyl.id) && Objects.equals(title,
                vinyl.title) && Objects.equals(artist, vinyl.artist) && Objects.equals(label, vinyl.label);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, label, releaseYear);
    }
    @Override
    public String toString() {
        return "Vinyl{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", label='" + label + '\'' +
                ", release_year=" + releaseYear +
                '}';
    }
}
