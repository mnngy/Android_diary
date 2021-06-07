package com.example.android_diary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBToDoHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo_db";
    private static final int DATABASE_VERSION = 1;

    public DBToDoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todo (" +
                "todoIndex INTEGER PRIMARY KEY AUTOINCREMENT," +
                "todoTitle TEXT," +
                "todoContent TEXT," +
                "todoDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todo");
        onCreate(db);
    }

    public ToDo select(String title) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo WHERE todoTitle = '" + title + "'", null);

        cursor.moveToNext();

        return new ToDo(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }

    public void update(String title, String content, String date, String previousTitle) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE todo SET " +
                "todoTitle = '" + title + "'" +
                ", todoContent = '" + content + "'" +
                ", todoDate = '" + date + "'" +
                " WHERE todoTitle = '" + previousTitle + "'");
    }

    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM todo WHERE todoTitle = '" + title + "'");
    }

    public void insert(String title, String content, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO todo(todoTitle, todoContent, todoDate)" +
                "VALUES('" + title + "','"+ content + "','" + date + "')");
    }
}
