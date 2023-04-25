package com.example.notebookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class NotebookDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notebook.db";
    private static final String TABLE_NAME = "notes";
    private static final int DATABASE_VERSION = 1;
    private static final String NOTE_ENTRY_ID = "_id";
    private static final String NOTE_ENTRY_TITLE = "_title";
    private static final String NOTE_ENTRY_DESCRIPTION = "_description";
    private static final String NOTE_ENTRY_CATEGORY = "_category";

    public NotebookDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + NOTE_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOTE_ENTRY_TITLE + " TEXT," + NOTE_ENTRY_DESCRIPTION + " TEXT," + NOTE_ENTRY_CATEGORY + " TEXT)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNote(Note note) {
        Log.d("TAG","the note from db is:"+note.category);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTE_ENTRY_TITLE, note.getTitle());
        values.put(NOTE_ENTRY_DESCRIPTION, note.getDescription());
        values.put(NOTE_ENTRY_CATEGORY, note.getCategory());
        return db.insert(TABLE_NAME, null, values);
    }

    // Retrieve all notes from the database
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notesList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
// String selection = NOTE_ENTRY_CATEGORY + " = ? OR " + NOTE_ENTRY_CATEGORY + " = ?";
// String[] selectionArgs = {"Urgent", "Important"};
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(NOTE_ENTRY_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_ENTRY_TITLE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_ENTRY_DESCRIPTION));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(NOTE_ENTRY_CATEGORY));
                Note note = new Note(title, description, category);
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return notesList;
    }
}
