package com.alexblogg.djehuti.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alexblogg.djehuti.database.entities.Note;

import java.util.List;

/**
 * Created by alexb on 18/11/2017.
 */

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE id LIKE :id")
    Note findNoteById(int id);

    @Query("SELECT * FROM note WHERE note_name LIKE :name")
    Note findNoteByName(String name);

    @Query("SELECT COUNT(*) FROM note")
    int countAllNotes();

    @Insert
    void insertAllNotes(Note... notes);

    @Update
    void updateAllNotes(Note... notes);

    @Delete
    void deleteNote(Note note);
}
