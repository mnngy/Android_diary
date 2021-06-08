package com.example.android_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateDiaryActivity extends AppCompatActivity {

    private EditText update_diary_title, update_diary_date, update_diary_content;
    private RadioButton btn_update_cry, btn_update_sad, btn_update_emoticon, btn_update_smile, btn_update_laughing;
    private DBDiaryHelper dbHelper;
    private String title, emotion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);

        getSupportActionBar().setTitle("나의 다이어리 조회/수정");

        update_diary_title = findViewById(R.id.update_diary_title);
        update_diary_date = findViewById(R.id.update_diary_date);
        update_diary_content = findViewById(R.id.update_diary_content);

        btn_update_cry = findViewById(R.id.btn_update_cry);
        btn_update_sad = findViewById(R.id.btn_update_sad);
        btn_update_emoticon = findViewById(R.id.btn_update_emoticon);
        btn_update_smile = findViewById(R.id.btn_update_smile);
        btn_update_laughing = findViewById(R.id.btn_update_laughing);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        dbHelper = new DBDiaryHelper(getApplicationContext());

        Diary diary = dbHelper.select(title);

        update_diary_title.setText(diary.getDiaryTitle());
        update_diary_date.setText(diary.getDiaryDate());
        update_diary_content.setText(diary.getDiaryContent());

        emotion = diary.getDiaryEmotion();

        switch (emotion) {
            case "매우 슬픔":
                btn_update_cry.setChecked(true);
                break;
            case "슬픔":
                btn_update_sad.setChecked(true);
                break;
            case "보통":
                btn_update_emoticon.setChecked(true);
                break;
            case "좋음":
                btn_update_smile.setChecked(true);
                break;
            case "매우 좋음":
                btn_update_laughing.setChecked(true);
                break;
            default: break;
        }
    }

    public void checkEmotionButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.btn_update_cry:
                if (checked) {
                    emotion = "매우 슬픔";
                }
                break;
            case R.id.btn_update_sad:
                if (checked) {
                    emotion = "슬픔";
                }
                break;
            case R.id.btn_update_emoticon:
                if (checked) {
                    emotion = "보통";
                }
                break;
            case R.id.btn_update_smile:
                if (checked) {
                    emotion = "좋음";
                }
                break;
            case R.id.btn_update_laughing:
                if (checked) {
                    emotion = "매우 좋음";
                }
                break;
        }
    }

    public void updateDiary(View view) {
        String title = update_diary_title.getText().toString();
        String content = update_diary_content.getText().toString();
        String date = update_diary_date.getText().toString();
        String emotion = this.emotion;
        String previousTitle = this.title;

        dbHelper.update(title, content, emotion, date, previousTitle);
        Toast.makeText(getApplicationContext(), "다이어리를 수정했습니다.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), MyDiaryActivity.class);
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
