package com.example.android_diary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    private int diaryIndex;
    private String diaryTitle;
    private String diaryContent;
    private String diaryDate;
}
