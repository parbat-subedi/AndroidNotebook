package com.example.notebookapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnAddNote;
    ArrayList<Note> notes;
    NotesAdapter adapter;

    NotebookDB dbHelper;
    LinearLayout llNoNotes;

    ActivityResultLauncher<Intent> resultIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK)
            {
                Intent intent = result.getData();
                String title = intent.getExtras().getString("note_title");
                String description = intent.getExtras().getString("note_description");
                String category = intent.getExtras().getString("note_category");
                Integer color = intent.getExtras().getInt("Color");
                Toast.makeText(MainActivity.this, "Title :" + title + " Description :" + category  , Toast.LENGTH_SHORT).show();

                Note note = new Note(title,description,category,color);
                adapter.addData(note);

                dbHelper.addNote(note);

                if(llNoNotes.getVisibility()==View.VISIBLE){
                    llNoNotes.setVisibility(View.GONE);
                }

            }
        }
    });


//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper= new NotebookDB(getApplicationContext());

        notes = new ArrayList<>();
        notes.addAll(dbHelper.getAllNotes());


        btnAddNote = findViewById(R.id.btnAddNote);
        RecyclerView rv = findViewById(R.id.recycleview_notes);

        llNoNotes= findViewById(R.id.llNoNotes);
        if(notes.size()<1){
            llNoNotes.setVisibility(View.VISIBLE);
        }else{
            llNoNotes.setVisibility(View.GONE);
        }

        adapter = new NotesAdapter(notes, new noteListener() {
            @Override
            public void onClick(Note note) {

                Log.d("TAG",""+note.category); //used for debugging in android

                Intent intent= new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("tvTitle",note.getTitle());
                intent.putExtra("tvDescription",note.getDescription());
                intent.putExtra("tvCategory",note.getCategory());
//                intent.putExtra("Color",note.getColor());
                startActivity(intent);

            }

            @Override
            public void onNoteEdit(Note note) {

            }

            @Override
            public void onNoteDelete(Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete this note?");
                builder.setTitle("Alert !");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes",(DialogInterface.OnClickListener)(dialog,which)->{
                    dbHelper.deleteNote(note.getId());
                    adapter.deleteNote(note);
                });
                builder.setNegativeButton("No",(DialogInterface.OnClickListener)(dialog,which)->{
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        rv.setAdapter(adapter);
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNotesActivity.class);
                resultIntent.launch(intent);
            }
        });


    }


}