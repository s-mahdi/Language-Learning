package com.example.mahdi.languagelearning.RoomDB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table", indices = @Index(value = {"word"}, unique = true))
public class Word implements Parcelable {

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

    protected Word(Parcel in) {
        wid = in.readInt();
        word = in.readString();
        passed = in.readByte() != 0;
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        @Override
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

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

    public int getWid() {
        return wid;
    }

    void setWid(int wid) {
        this.wid = wid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(wid);
        parcel.writeString(word);
        parcel.writeByte((byte) (passed ? 1 : 0));
    }
}
