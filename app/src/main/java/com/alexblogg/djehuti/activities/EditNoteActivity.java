package com.alexblogg.djehuti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alexblogg.djehuti.C;
import com.alexblogg.djehuti.R;

/**
 * Created by alexb on 18/11/2017.
 */

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG = "EditNoteActivity";

    EditText name;
    EditText text;
    Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);

        name = findViewById(R.id.note_name);
        text = findViewById(R.id.note_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.d(TAG, "onClick: Save button clicked");

                String nameString = name.getText().toString();
                String textString = text.getText().toString();

                if ((!nameString.equals("")) && (!textString.equals(""))) {

                    Intent intent = new Intent();
                    intent.putExtra(C.INTENT_NAME_FIELD, nameString);
                    intent.putExtra(C.INTENT_TEXT_FIELD, textString);

                    if (getIntent().getStringExtra(C.INTENT_GOAL_FIELD).equals(C.GOAL_ADD)) {
                        setResult(C.INTENT_RESULT_ADD_NOTE, intent);
                        //Log.d(TAG, "onClick: the goal is to add");
                    } else if (getIntent().getStringExtra(C.INTENT_GOAL_FIELD).equals(C.GOAL_EDIT)) {
                        setResult(C.INTENT_RESULT_EDIT_NOTE, intent);
                        //Log.d(TAG, "onClick: the goal is to edit");
                    }

                    //Log.d(TAG, "onClick: the activity will now finish");

                    finish();


                }
            }
        });
    }
}
