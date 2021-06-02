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

public class MyToDoActivity extends AppCompatActivity {

    private ListView lv_my_todo;
    private FloatingActionButton btn_todo_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_todo);

        getSupportActionBar().setTitle("나의 ToDo");

        lv_my_todo = findViewById(R.id.lv_my_todo);

        displayList();
    }

    protected void displayList() {
        DBToDoHelper dbHelper = new DBToDoHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo", null);

        ToDoListViewAdapter adapter = new ToDoListViewAdapter();

        while (cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(1), cursor.getString(2));
        }

        lv_my_todo.setAdapter(adapter);
    }

    public void moveAddToDo(View view) {
        Intent intent = new Intent(getApplicationContext(), AddToDoActivity.class);
        startActivity(intent);
    }
}
