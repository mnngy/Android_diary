package com.example.android_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListViewAdapter extends BaseAdapter {

    List<ToDo> list = new ArrayList<>();

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
            view = inflater.inflate(R.layout.item_todo_design, null);
        }

        TextView text1 = view.findViewById(R.id.text1);
        TextView text2 = view.findViewById(R.id.text2);

        ToDo todo = list.get(position);

        text1.setText(todo.getTodoTitle());
        text2.setText(todo.getTodoDate());

        return view;
    }

    public void addItemToList(String title, String date) {
        ToDo todo = new ToDo();

        todo.setTodoTitle(title);
        todo.setTodoDate(date);

        list.add(todo);
    }
}
