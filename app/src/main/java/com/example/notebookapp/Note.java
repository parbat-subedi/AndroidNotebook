package com.example.notebookapp;

public class Note {
    String title, description, category;

    public Note(String title, String description, String categoty) {
        this.title = title;
        this.description = description;
        this.category = category;
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
}
