package com.task.lecture9.domain.Form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateForm {

    private final Integer id;

    @NotBlank
    @Length(max = 30, message = "titleは100文字以内で入力してください")
    private String title;


    @NotBlank
    @Length(max = 30)
    private String artist;

    @NotBlank
    @Length(max = 30)
    private String label;

    @NotNull
    private int release_year;

    public CreateForm(Integer id, String title, String artist, String label, int release_year) {
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
    public String getArtist() {
        return artist;
    }
    public String getLabel() {
        return label;
    }
    public int getRelease_year() {
        return release_year;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }
}
