package com.example.android_diary;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        
        getSupportActionBar().setTitle("목록");

        ListView listView = findViewById(R.id.listView);

        List<String> list = new ArrayList<>();
        list.add("ToDo");
        list.add("Diary");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_type, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);

                if (data.equals("ToDo")) {
                    System.out.println("todo");
                } else {
                    System.out.println("diary");
                }
            }
        });
    }
}
