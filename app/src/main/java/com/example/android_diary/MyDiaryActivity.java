package com.example.android_diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyDiaryActivity extends AppCompatActivity {

    ListView lv_my_diary;
    FloatingActionButton btn_diary_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        getSupportActionBar().setTitle("나의 다이어리");

        lv_my_diary = findViewById(R.id.lv_my_diary);

        displayList();
    }

    protected void displayList() {
        DBDiaryHelper dbHelper = new DBDiaryHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM diary", null);

        DiaryListViewAdapter adapter = new DiaryListViewAdapter();

        while (cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(1), cursor.getString(2));
        }

        lv_my_diary.setAdapter(adapter);
    }

    public void moveAddDiary(View view) {
        Intent intent = new Intent(getApplicationContext(), AddDiaryActivity.class);
        startActivity(intent);
    }
}
