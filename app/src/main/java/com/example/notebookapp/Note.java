package com.example.notebookapp;

public class Note {


    String title, description, category;
    Integer Color;
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Note(String title, String description, String category, Integer color, Long id) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.Color = color;
        this.id = id;
    }


    public Note(String title, String description, String category, Integer Color) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.Color= Color;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Integer getColor(){return  Color;}
}
