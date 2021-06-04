package com.example.android_diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyToDoActivity extends AppCompatActivity {

    private DBToDoHelper dbHelper;
    private SQLiteDatabase db;
    private String[] items;

    private ListView lv_my_todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_todo);

        getSupportActionBar().setTitle("나의 ToDo");

        items = new String[]{"조회", "수정", "삭제"};

        lv_my_todo = findViewById(R.id.lv_my_todo);
        lv_my_todo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(parent.getContext());
                builder.setTitle("선택해주세요.");

                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });

        displayList();
    }

    protected void displayList() {
        dbHelper = new DBToDoHelper(this);
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM todo", null);

        ToDoListViewAdapter adapter = new ToDoListViewAdapter();

        while (cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(1), cursor.getString(3));
        }

        lv_my_todo.setAdapter(adapter);
    }

    public void moveAddToDo(View view) {
        Intent intent = new Intent(getApplicationContext(), AddToDoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        switch (id) {
            case R.id.item_category:
                intent = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.item_todo:
                intent = new Intent(getApplicationContext(), MyToDoActivity.class);
                startActivity(intent);
                break;
            case R.id.item_diary:
                intent = new Intent(getApplicationContext(), MyDiaryActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
