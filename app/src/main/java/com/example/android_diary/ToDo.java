package com.example.android_diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {
    private int todoIndex;
    private String todoTitle;
    private String todoContent;
    private String todoDate;
}
