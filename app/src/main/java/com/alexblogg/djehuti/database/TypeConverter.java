package com.alexblogg.djehuti.database;

import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by alexb on 18/11/2017.
 */

public class TypeConverter {

    @android.arch.persistence.room.TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @android.arch.persistence.room.TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
