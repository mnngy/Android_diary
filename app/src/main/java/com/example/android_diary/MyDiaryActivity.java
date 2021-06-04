package com.example.android_diary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyDiaryActivity extends AppCompatActivity {

    private ListView lv_my_diary;
    private String[] items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        getSupportActionBar().setTitle("나의 다이어리");

        items = new String[]{"조회", "수정", "삭제"};

        lv_my_diary = findViewById(R.id.lv_my_diary);
        lv_my_diary.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
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
        DBDiaryHelper dbHelper = new DBDiaryHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM diary", null);

        DiaryListViewAdapter adapter = new DiaryListViewAdapter();

        while (cursor.moveToNext()) {
            adapter.addItemToList(cursor.getString(1), cursor.getString(4), cursor.getString(3));
        }

        lv_my_diary.setAdapter(adapter);
    }

    public void moveAddDiary(View view) {
        Intent intent = new Intent(getApplicationContext(), AddDiaryActivity.class);
        startActivity(intent);
    }
}
