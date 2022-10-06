package com.task.lecture9.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

public class InsertForm {

    @NotBlank(message = "titleを入力をしてください")
    @Length(max = 50, message = "titleは50文字以内で入力してください")
    private final String title;


    @NotBlank(message = "artistを入力をしてください")
    @Length(max = 50, message = "artistは50文字以内で入力してください")
    private final String artist;

    @NotBlank(message = "labelを入力をしてください")
    @Length(max = 50, message = "labelは50文字以内で入力してください")
    private final String label;

    @Digits(integer = 4, fraction = 0, message = "release_yearは4桁で入力してください")
    private final int releaseYear;

    public InsertForm(String title, String artist, String label, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.releaseYear = releaseYear;
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
    public int getReleaseYear() {
        return releaseYear;
    }
}