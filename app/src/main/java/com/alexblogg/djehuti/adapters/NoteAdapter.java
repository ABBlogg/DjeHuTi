package com.alexblogg.djehuti.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexblogg.djehuti.R;
import com.alexblogg.djehuti.database.entities.Note;

import java.util.List;

/**
 * Created by alexb on 18/11/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    List<Note> notes;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(notes.get(position).getName());
        holder.dateCreated.setText("Created: " + notes.get(position).getDateCreated().toString());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView dateCreated;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.note_name);
            dateCreated = itemView.findViewById(R.id.note_date_created);
        }
    }
}
