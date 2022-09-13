package com.task.lecture9.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
public class InsertForm {

    @NotBlank(message = "titleを入力をしてください")
    @Length(max = 50, message = "titleは50文字以内で入力してください")
    private String title;


    @NotBlank(message = "artistを入力をしてください")
    @Length(max = 50, message = "artistは50文字以内で入力してください")
    private String artist;

    @NotBlank(message = "labelを入力をしてください")
    @Length(max = 50, message = "labelは50文字以内で入力してください")
    private String label;

    @NotBlank(message = "releaseYearを入力してください")
    @Length(min = 4, max = 4, message = "releaseYearは4桁で入力してください")
    private String releaseYear;

    public InsertForm(String title, String artist, String label, String releaseYear) {
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
        InsertForm that = (InsertForm) o;
        return releaseYear == that.releaseYear && title.equals(that.title) && artist.equals(that.artist) && label.equals(that.label);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, artist, label, releaseYear);
    }
}
