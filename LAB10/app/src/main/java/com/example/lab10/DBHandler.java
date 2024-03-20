package com.example.lab10;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "notedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mynotes";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String DATE = "date";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT,"
                + CONTENT + " TEXT,"
                + DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(query);
    }

    public void addNewNote(String noteTitle, String noteContent, String noteDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, noteTitle);
        values.put(CONTENT, noteContent);
        values.put(DATE, noteDate);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<NoteModal> getAllNotes() {
        ArrayList<NoteModal> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                NoteModal note = new NoteModal(id, title, content, date);
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        return notesList;
    }

    public void deleteNote(String title, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TITLE + " = ? AND " + DATE + " = ?", new String[]{title, date});
        db.close();
    }

    public void updateNote(String title, String content, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(CONTENT, content);
        values.put(DATE, date);
        db.update(TABLE_NAME, values, TITLE + " = ? AND " + DATE + " = ?", new String[]{title, date});
        db.close();
    }
}
