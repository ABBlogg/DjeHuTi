package com.alexblogg.djehuti.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexblogg.djehuti.C;
import com.alexblogg.djehuti.R;
import com.alexblogg.djehuti.activities.EditNoteActivity;
import com.alexblogg.djehuti.database.entities.Note;
import com.alexblogg.djehuti.fragments.NoteFragment;

import java.util.List;

/**
 * Created by alexb on 18/11/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static final String TAG = "NoteAdapter";

    List<Note> notes;
    FragmentActivity activity;

    public NoteAdapter(List<Note> notes, FragmentActivity activity) {
        this.notes = notes;
        this.activity = activity;
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_row, parent, false);
        return new ViewHolder(view, activity);
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

        private FragmentActivity activity;

        public ViewHolder(final View itemView, final FragmentActivity activity) {
            super(itemView);
            name = itemView.findViewById(R.id.note_name);
            dateCreated = itemView.findViewById(R.id.note_date_created);

            this.activity = activity;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d(TAG, "onClick: pos: " + getAdapterPosition());

                    Intent intent = new Intent();
                    intent.setClass(activity.getApplicationContext(), EditNoteActivity.class);
                    intent.putExtra(C.INTENT_GOAL_FIELD, C.GOAL_EDIT);
                    intent.putExtra(C.INTENT_NAME_FIELD, notes.get(getAdapterPosition()).getName());
                    intent.putExtra(C.INTENT_TEXT_FIELD, notes.get(getAdapterPosition()).getText());
                    intent.putExtra(C.INTENT_ID_FIELD, notes.get(getAdapterPosition()).getId());
                    (activity).startActivityForResult(intent, C.INTENT_REQUEST_EDIT_NOTE);
                }
            });
        }
    }
}
