package com.example.notebookapp;

public class Note {
    String title, description, category;
    Integer Color;

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
