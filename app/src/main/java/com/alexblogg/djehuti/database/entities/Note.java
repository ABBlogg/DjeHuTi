package com.alexblogg.djehuti.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by alexb on 18/11/2017.
 */

@Entity(indices = {@Index(value = "note_name", unique = true)})
public class Note {

    //region variables

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "note_name")
    private String name;

    @ColumnInfo(name = "note_date_created")
    private Date dateCreated;

    @ColumnInfo(name = "note_date_edited")
    private Date dateEdited;

    @ColumnInfo(name = "note_text")
    private String text;

    //endregion

    public Note(String name) {
        this.name = name;
        this.dateCreated = new Date();
        this.dateEdited = dateCreated;
        this.text = new String();
    }

    public void updateText(String text) {
        this.text = text;
        updateDate();
    }

    public void updateName(String name) {
        this.name = name;
        updateDate();
    }

    public void updateNameAndText(String name, String text) {
        this.name = name;
        this.text = text;
        updateDate();
    }

    public void updateDate() {
        this.dateEdited = new Date();
    }

    //region getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateEdited() {
        return dateEdited;
    }

    public String getText() {
        return text;
    }

    //endregion

    //region setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateEdited(Date dateEdited) {
        this.dateEdited = dateEdited;
    }

    public void setText(String text) {
        this.text = text;
    }

    //endregion
}
