package com.example.android_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddDiaryActivity extends AppCompatActivity {

    private EditText edit_diary_title, edit_diary_content, edit_diary_date;
    private FloatingActionButton btn_diary_done;

    private DBDiaryHelper dbHelper;

    private String emotion;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        getSupportActionBar().setTitle("다이어리 작성");

        edit_diary_title = findViewById(R.id.edit_diary_title);
        edit_diary_content = findViewById(R.id.edit_diary_content);
        edit_diary_date = findViewById(R.id.edit_diary_date);
        btn_diary_done = findViewById(R.id.btn_diary_done);

        btn_diary_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edit_diary_title.getText().toString();
                String content = edit_diary_content.getText().toString();
                String date = edit_diary_date.getText().toString();

                dbHelper = new DBDiaryHelper(getApplicationContext());

                dbHelper.insert(title, content, emotion, date);

                Toast.makeText(getApplicationContext(), "다이어리 작성을 하셨습니다.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MyDiaryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkEmotionButton(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.btn_cry:
                if (checked) {
                    emotion = "매우 슬픔";
                }
                break;
            case R.id.btn_sad:
                if (checked) {
                    emotion = "슬픔";
                }
                break;
            case R.id.btn_emoticon:
                if (checked) {
                    emotion = "보통";
                }
                break;
            case R.id.btn_smile:
                if (checked) {
                    emotion = "좋음";
                }
                break;
            case R.id.btn_laughing:
                if (checked) {
                    emotion = "매우 좋음";
                }
                break;
        }
    }
}
