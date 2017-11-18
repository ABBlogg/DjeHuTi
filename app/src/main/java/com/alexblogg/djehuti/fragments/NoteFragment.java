package com.alexblogg.djehuti.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexblogg.djehuti.C;
import com.alexblogg.djehuti.R;
import com.alexblogg.djehuti.activities.EditNoteActivity;
import com.alexblogg.djehuti.activities.MainActivity;
import com.alexblogg.djehuti.adapters.NoteAdapter;
import com.alexblogg.djehuti.database.entities.Note;

import java.util.List;

public class NoteFragment extends Fragment {

    private static final String TAG = "NoteFragment";

    RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;

    FloatingActionButton add;

    List<Note> notes;

    public NoteFragment() {
        // Required empty public constructor
    }

    public static NoteFragment newInstance() {
        NoteFragment fragment = new NoteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.note_fragment, container, false);

        notes = MainActivity.db.noteDAO().getAllNotes();

        recyclerView = view.findViewById(R.id.note_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new NoteAdapter(notes, this.getActivity());
        recyclerView.setAdapter(adapter);

        add = (FloatingActionButton) view.findViewById(R.id.add_fab);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditNoteActivity.class);
                intent.putExtra(C.INTENT_GOAL_FIELD, C.GOAL_ADD);
                startActivityForResult(intent, C.INTENT_REQUEST_ADD_NOTE);
            }
        });

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Log.d(TAG, "onActivityResult: ReqCode: " + requestCode + ", ResCode: " + resultCode);
//
//        if (resultCode == C.INTENT_RESULT_ADD_NOTE) {
//            String name = data.getStringExtra(C.INTENT_NAME_FIELD);
//            String text = data.getStringExtra(C.INTENT_TEXT_FIELD);
//
//            Log.d(TAG, "onActivityResult: name: " + name + ", text: " + text);
//
//            Note note = new Note(name);
//            note.updateText(text);
//            MainActivity.db.noteDAO().insertAllNotes(note);
//        }
//        else if (resultCode == C.INTENT_RESULT_EDIT_NOTE) {
//            String name = data.getStringExtra(C.INTENT_NAME_FIELD);
//            String text = data.getStringExtra(C.INTENT_TEXT_FIELD);
//
//            Log.d(TAG, "onActivityResult: name: " + name + ", text: " + text);
//
//            Note note = MainActivity.db.noteDAO().findNoteByName(name);
//            note.updateText(text);
//            MainActivity.db.noteDAO().updateAllNotes(note);
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
