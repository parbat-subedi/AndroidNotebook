package com.example.notebookapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    ArrayList<Note> notes;
    noteListener noteListner;

    public NotesAdapter(ArrayList<Note> notes, noteListener noteListner) {
        this.notes = notes;
        this.noteListner= noteListner;
    }

    public void addData(Note note){
        notes.add(note);
        notifyItemInserted(notes.size());
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view,parent,false);
        return new  NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bindView(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description, category;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.display_note_category);
            title = itemView.findViewById(R.id.display_note_title);
            description = itemView.findViewById(R.id.display_note_description);

        }

        public void bindView(Note note){
            category.setText(note.getCategory());
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            itemView.setBackgroundColor(note.getColor());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteListner.onClick(note);
            }
        });
        }
    }
}


