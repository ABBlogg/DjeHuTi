package com.alexblogg.djehuti.database;

import android.arch.persistence.room.*;

import com.alexblogg.djehuti.database.daos.NoteDAO;
import com.alexblogg.djehuti.database.entities.Note;

/**
 * Created by alexb on 18/11/2017.
 */

@Database(entities = {Note.class}, version = 1)
@TypeConverters(value = TypeConverter.class)
public abstract class DHTDataBase extends RoomDatabase {

    public abstract NoteDAO noteDAO();

}
