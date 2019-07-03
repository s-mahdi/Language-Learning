package com.example.mahdi.languagelearning.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table", indices = @Index(value = {"word"}, unique = true))
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wid")
    private int wid;//word id

    @ColumnInfo(name = "word")
    private String word;

    private boolean passed;

    public Word(String word, boolean passed) {
        this.word = word;
        this.passed = passed;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    int getWid() {
        return wid;
    }

    void setWid(int wid) {
        this.wid = wid;
    }


}
