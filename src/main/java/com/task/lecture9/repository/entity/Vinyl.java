package com.task.lecture9.repository.entity;

import java.util.Objects;

public class Vinyl {

    private final Integer id;
    private String title;
    private String artist;
    private String label;
    private String releaseYear;

    public Vinyl(int id, String title, String artist, String label, String releaseYear) {
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
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
        return id.equals(vinyl.id) && title.equals(vinyl.title) && artist.equals(vinyl.artist) && label.equals(vinyl.label) && releaseYear.equals(vinyl.releaseYear);
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
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
