package com.task.lecture9.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class UpdateForm {

    @Length(max = 50, message = "titleは50文字以内で入力してください")
    private final String title;

    @Length(max = 50, message = "artistは50文字以内で入力してください")
    private final String artist;

    @Length(max = 50, message = "labelは50文字以内で入力してください")
    private final String label;

    @Pattern(regexp = "^\\d{4}?$", message = "整数4桁で入力してください")
    private final String releaseYear;

    public UpdateForm(String title, String artist, String label, String releaseYear) {
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
    public String getReleaseYear() {
        return releaseYear;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateForm that = (UpdateForm) o;
        return title.equals(that.title) && artist.equals(that.artist) && label.equals(that.label) && releaseYear.equals(that.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, label, releaseYear);
    }
}
