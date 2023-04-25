package com.example.notebookapp;

public interface noteListener {
    void onClick(Note note);
    void onNoteEdit(Note note);
    void onNoteDelete(Note note);
}
