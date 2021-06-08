package com.example.android_diary;

import android.content.Context;
import android.database.Cursor;
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
                "diaryEmotion TEXT," + // 1 ~ 5
                "diaryDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(db);
    }

    public void insert(String title, String content, String emotion, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO diary(diaryTitle, diaryContent, diaryEmotion, diaryDate)" +
                "VALUES('" + title + "','"+ content + "','" + emotion + "','" + date + "');");
    }

    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM diary WHERE diaryTitle = '" + title + "'");
    }

    public Diary select(String title) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM diary WHERE diaryTitle = '" + title + "'", null);

        cursor.moveToNext();

        return new Diary(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );
    }

    public void update(String title, String content, String emotion, String date, String previousTitle) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE diary SET " +
                "diaryTitle = '" + title + "'" +
                ", diaryContent = '" + content + "'" +
                ", diaryEmotion = '" + emotion + "'" +
                ", diaryDate = '" + date + "'" +
                " WHERE diaryTitle = '" + previousTitle + "'");
    }
}
