package com.example.android_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DiaryListViewAdapter extends BaseAdapter {

    List<Diary> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Context context = parent.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_diary_design, null);
        }

        TextView text1 = view.findViewById(R.id.text1);
        TextView text2 = view.findViewById(R.id.text2);
        TextView text3 = view.findViewById(R.id.text3);

        Diary diary = list.get(position);

        text1.setText(diary.getDiaryTitle());
        text2.setText(diary.getDiaryDate());
        text3.setText(diary.getDiaryEmotion());

        return view;
    }

    public void addItemToList(String title, String date, String emotion) {
        Diary diary = new Diary();

        diary.setDiaryTitle(title);
        diary.setDiaryEmotion(emotion);
        diary.setDiaryDate(date);

        list.add(diary);
    }
}
