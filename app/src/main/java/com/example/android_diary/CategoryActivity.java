package com.example.android_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        getSupportActionBar().setTitle("목록");

        ListView listView = findViewById(R.id.lv_my_diary);

        List<String> list = new ArrayList<>();
        list.add("ToDo");
        list.add("Diary");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_category_design, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);

                Intent intent;

                if (data.equals("ToDo")) {
                    intent = new Intent(getApplicationContext(), MyToDoActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), MyDiaryActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}
