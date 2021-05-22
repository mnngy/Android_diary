package com.example.android_diary;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diary);

        getSupportActionBar().setTitle("나의 다이어리");

        ListView listView = findViewById(R.id.listView);

        // ListView에 넣을 값 할당
        ArrayList<HashMap<String, String>> diaryList = new ArrayList<>();
        HashMap<String, String> item;

        item = new HashMap<>();
        item.put("key1", "첫 번째 일기");
        item.put("key2", "21.5.18");
        diaryList.add(item);

        item = new HashMap<>();
        item.put("key1", "두 번째 일기");
        item.put("key2", "21.5.18");
        diaryList.add(item);

        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(), // context
                diaryList, // 연동하는 data
                R.layout.my_diary_list_type, // layout
                new String[] {"key1", "key2"}, // 데이터 추출하기 위한 key
                new int[] {R.id.text1, R.id.text2} // 레이아웃 파일 내 뷰 id
        );
        listView.setAdapter(adapter);
    }
}
