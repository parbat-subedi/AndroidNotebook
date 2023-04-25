package com.example.notebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ListView extends AppCompatActivity {

    TextView displayCategory, displayTitle, displayDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_view);

        displayCategory = findViewById(R.id.display_note_category);
        displayTitle = findViewById(R.id.display_note_title);
        displayDescription = findViewById(R.id.display_note_description);
    }
}