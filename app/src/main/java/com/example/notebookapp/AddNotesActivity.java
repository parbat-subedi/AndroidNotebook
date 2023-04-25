package com.example.notebookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNotesActivity extends AppCompatActivity {

    EditText editTitle, editDescriptiion;
    Spinner category;
    Button addNotes;
    String noteCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        editTitle = findViewById(R.id.edit_note_title);
        category = findViewById(R.id.spinerCategory);
        editDescriptiion = findViewById(R.id.edit_note_description);
        addNotes = findViewById(R.id.add_note);

        String[] items = {"Normal", "Urgent", "Normal"};
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        category.setAdapter(spinnerAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                noteCategory = category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String description = editDescriptiion.getText().toString();

                if (validate(title, description)) {
                    Intent intent = new Intent();
                    intent.putExtra("note_title", title);
                    intent.putExtra("note_description", description);
                    intent.putExtra("note_category", category.getSelectedItem().toString());

                    setResult(RESULT_OK,intent);
                    finish();

                }else {
                      Toast.makeText(AddNotesActivity.this, "Validate data", Toast.LENGTH_SHORT).show();

                }



            }
        });
    }

    public boolean validate(String title,String description){
        if (!title.isEmpty() && !description.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

}