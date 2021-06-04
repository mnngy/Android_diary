package com.example.android_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView imageView = view.findViewById(R.id.imageView_diary);

        Diary diary = list.get(position);

        text1.setText(diary.getDiaryTitle());
        text2.setText(diary.getDiaryDate());

        String diaryEmotion = diary.getDiaryEmotion();

        switch (diaryEmotion) {
            case "매우 슬픔":
                imageView.setImageResource(R.drawable.ic_cry);
                break;
            case "슬픔":
                imageView.setImageResource(R.drawable.ic_sad);
                break;
            case "보통":
                imageView.setImageResource(R.drawable.ic_emoticon);
                break;
            case "좋음":
                imageView.setImageResource(R.drawable.ic_smile);
                break;
            case "매우 좋음":
                imageView.setImageResource(R.drawable.ic_laughing);
                break;
            default: break;
        }
        return view;
    }

    public void addItemToList(String title, String date, String emotion) {
        Diary diary = new Diary();

        diary.setDiaryTitle(title);
        diary.setDiaryDate(date);
        diary.setDiaryEmotion(emotion);

        list.add(diary);
    }
}
