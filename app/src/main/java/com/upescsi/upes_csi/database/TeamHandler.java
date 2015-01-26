package com.upescsi.upes_csi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "team.db";
    private static final String TABLE_TEAM = "team";

    private static final String COLUMN_SR_NO = "sr_no";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESIGNATION = "designation";

    public TeamHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_TEAM + "("
                + COLUMN_SR_NO + " INTEGER,"
                + COLUMN_NAME + " TEXT," + COLUMN_DESIGNATION + " TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        onCreate(db);
    }

    public void addEvent(Event event) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SR_NO, event.getEventNo());
        contentValues.put(COLUMN_NAME, event.getEventTitle());
        contentValues.put(COLUMN_DESIGNATION, event.getEventSummary());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_TEAM, null, contentValues);
        sqLiteDatabase.close();
    }

    public Event findEvent(String eventName) {
        String query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + COLUMN_NAME
                + "= \"" + eventName + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Event event = new Event();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            event.setEventNo(Integer.parseInt(cursor.getString(0)));
            event.setEventTitle(cursor.getString(1));
            event.setEventSummary(cursor.getString(2));
            cursor.close();
        } else {
            event = null;
        }
        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<Event>();
        String query = "SELECT * FROM " + TABLE_TEAM;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        // Looping through all rows and adding them to the list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setEventNo(Integer.parseInt(cursor.getString(0)));
                event.setEventTitle(cursor.getString(1));
                event.setEventSummary(cursor.getString(2));
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
        sqLiteDatabase.delete(TABLE_TEAM, null, null);
        sqLiteDatabase.close();
    }
}
