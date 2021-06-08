package com.example.android_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateToDoActivity extends AppCompatActivity {

    private EditText update_todo_title, update_todo_date, update_todo_content;
    private DBToDoHelper dbHelper;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_todo);

        getSupportActionBar().setTitle("나의 ToDo 조회/수정");

        update_todo_title = findViewById(R.id.update_todo_title);
        update_todo_date = findViewById(R.id.update_todo_date);
        update_todo_content = findViewById(R.id.update_todo_content);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        dbHelper = new DBToDoHelper(getApplicationContext());

        ToDo todo = dbHelper.select(title);

        update_todo_title.setText(todo.getTodoTitle());
        update_todo_date.setText(todo.getTodoDate());
        update_todo_content.setText(todo.getTodoContent());
    }

    public void updateToDo(View view) {
        String title = update_todo_title.getText().toString();
        String content = update_todo_content.getText().toString();
        String date = update_todo_date.getText().toString();
        String previousTitle = this.title;

        dbHelper.update(title, content, date, previousTitle);
        Toast.makeText(getApplicationContext(), "ToDo 를 수정했습니다.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MyToDoActivity.class);
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
