package com.upescsi.upes_csi.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal on 20-01-2015.
 */
public class EventHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    private static final String TABLE_EVENTS = "events";

    private static final String COLUMN_EVENT_NO = "event_no";
    private static final String COLUMN_EVENT_TITLE = "title";
    private static final String COLUMN_EVENT_IMG_WIDTH = "img_width";

    public EventHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + COLUMN_EVENT_NO + " INTEGER,"
                + COLUMN_EVENT_TITLE + " TEXT,"
                + COLUMN_EVENT_IMG_WIDTH + " INTEGER" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void addEvent(Event event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EVENT_NO, event.getEventNo());
        contentValues.put(COLUMN_EVENT_TITLE, event.getEventTitle());
        contentValues.put(COLUMN_EVENT_IMG_WIDTH, event.getEventImgWidth());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_EVENTS, null, contentValues);
        sqLiteDatabase.close();
    }

    public Event findEvent(String eventName) {
        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE " + COLUMN_EVENT_TITLE
                + "= \"" + eventName + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Event event = new Event();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setEventNo(Integer.parseInt(cursor.getString(0)));
            event.setEventTitle(cursor.getString(1));
            event.setEventImgWidth(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        } else {
            event = null;
        }
        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();
        String query = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        // Looping through all rows and adding them to the list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setEventNo(Integer.parseInt(cursor.getString(0)));
                event.setEventTitle(cursor.getString(1));
                event.setEventImgWidth(Integer.parseInt(cursor.getString(2)));
                // Adding them to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return eventList;
    }

    public void clear() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_EVENTS, null, null);
        sqLiteDatabase.close();
    }

    public void updateEvent(Event event) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EVENT_IMG_WIDTH, event.getEventImgWidth());
        sqLiteDatabase.update(TABLE_EVENTS, contentValues, COLUMN_EVENT_NO + "=" + event.getEventNo(), null);
        sqLiteDatabase.close();
    }
}
