package com.example.android_diary;

import android.content.Context;
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

    public void insert(String title, String content, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO todo(todoTitle, todoContent, todoDate)" +
                "VALUES('" + title + "','"+ content + "','" + date + "')");
    }

    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM todo WHERE todoTitle = '" + title + "'");
    }
}
