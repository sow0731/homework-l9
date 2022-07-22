package com.task.lecture9.domain.Form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateForm {

    private Integer id;

    @NotBlank
    @Length(max = 100)
    private String title;


    @NotBlank
    @Length(max = 100)
    private String artist;

    @NotBlank
    @Length(max = 100)
    private String label;


    @NotNull
    private int year;


    public CreateForm() {
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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

}
