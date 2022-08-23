package com.task.lecture9.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class InsertForm {

    @NotBlank(message = "titleを入力をしてください")
    @Length(max = 30, message = "titleは30文字以内で入力してください")
    private String title;


    @NotBlank(message = "artistを入力をしてください")
    @Length(max = 30, message = "artistは30文字以内で入力してください")
    private String artist;

    @NotBlank(message = "labelを入力をしてください")
    @Length(max = 30, message = "labelは30文字以内で入力してください")
    private String label;

    @Digits(integer = 4, fraction = 0, message = "release_yearは4桁で入力してください")
    private int release_year;

    public InsertForm(Integer id, String title, String artist, String label, int release_year) {
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.release_year = release_year;
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
