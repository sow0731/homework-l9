package com.task.lecture9.dto;

import java.util.Objects;

public class VinylDto {
    private final Integer id;
    private String title;
    private String artist;
    private String label;
    private int releaseYear;

    public VinylDto(String title, String artist, String label, int releaseYear) {
        this.id = 0;
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
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
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
        VinylDto vinylDto = (VinylDto) o;
        return releaseYear == vinylDto.releaseYear && Objects.equals(id, vinylDto.id) && Objects.equals(title,
                vinylDto.title) && Objects.equals(artist, vinylDto.artist) && Objects.equals(label, vinylDto.label);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, label, releaseYear);
    }
    @Override
    public String toString() {
        return "VinylDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", label='" + label + '\'' +
                ", release_year=" + releaseYear +
                '}';
    }
}