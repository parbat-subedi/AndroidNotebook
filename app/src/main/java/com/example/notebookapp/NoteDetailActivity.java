package com.example.notebookapp;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NoteDetailActivity extends AppCompatActivity {
//    String title, description, Category;
TextView tvTitle,tvCategory,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        tvTitle = findViewById(R.id.title);
        tvCategory = findViewById(R.id.category);
        tvDescription = findViewById(R.id.description);

        Intent intent = getIntent();
        String title= intent.getExtras().getString("tvTitle");
        String desc=intent.getExtras().getString("tvDescription");
        String cat=intent.getExtras().getString("tvCategory");

        tvTitle.setText(title);
        tvCategory.setText(cat);
        tvDescription.setText(desc);
        }
}

