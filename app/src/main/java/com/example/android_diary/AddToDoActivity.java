package com.example.android_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddToDoActivity extends AppCompatActivity {

    EditText edit_todo_title, edit_todo_content, edit_todo_date;
    FloatingActionButton btn_todo_done;

    DBToDoHelper dbHelper;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        getSupportActionBar().setTitle("ToDo 작성");

        edit_todo_title = findViewById(R.id.edit_todo_title);
        edit_todo_content = findViewById(R.id.edit_todo_content);
        edit_todo_date = findViewById(R.id.edit_todo_date);
        btn_todo_done = findViewById(R.id.btn_todo_done);

        btn_todo_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edit_todo_title.getText().toString();
                String content = edit_todo_content.getText().toString();
                String date = edit_todo_date.getText().toString();

                dbHelper = new DBToDoHelper(getApplicationContext());

                dbHelper.insert(title, content, date);

                Toast.makeText(getApplicationContext(), "ToDo 작성을 하셨습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MyToDoActivity.class);
                startActivity(intent);
            }
        });
    }

}
