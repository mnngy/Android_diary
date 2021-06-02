package com.example.android_diary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDiaryHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "diary_db";
    private static final int DATABASE_VERSION = 1;

    public DBDiaryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE diary (" +
                "diaryIndex INTEGER PRIMARY KEY AUTOINCREMENT," +
                "diaryTitle TEXT," +
                "diaryContent TEXT," +
                "diaryDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public void insert(String title, String content, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO diary(diaryTitle, diaryContent, diaryDate)" +
                "VALUES('" + title + "','"+ content + "','" + date + "');");
    }
}
