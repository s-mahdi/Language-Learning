package com.example.mahdi.languagelearning;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.mahdi.languagelearning.RoomDB.Word;
import com.example.mahdi.languagelearning.RoomDB.WordViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    Button buttonA, buttonB, buttonC, buttonD, nextButton;
    ImageView playSound;

    ArrayList<Character> optionsList;

    TextToSpeech mTextToSpeech;

    Word correctWord;

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Objects.requireNonNull(getSupportActionBar()).hide();

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.US);
                }
            }
        });

        Intent i = getIntent();
        correctWord = i.getParcelableExtra(TrainingActivity.EXTRA_WORD);

        setRandomWords();

        setView();

        // TODO: 07/07/2019 play sound when activity starts

    }


    private void setRandomWords() {

        optionsList = new ArrayList<>();

        ArrayList<Integer> list = new ArrayList<>();
        char mChar = correctWord.getWord().charAt(0);
        int ascii = (int) mChar; // this is the ascii code of word
        for (int i = 65; i <= 90; i++) { // 65 is ascii code for A and 90 for Z
            if (i != ascii)
                list.add(i);
        }
        Collections.shuffle(list);

        optionsList.add((char) list.get(0).intValue());
        optionsList.add((char) list.get(1).intValue());
        optionsList.add((char) list.get(2).intValue());
        optionsList.add(correctWord.getWord().charAt(0));
    }


    private void setView() {
        buttonA = findViewById(R.id.quizOption1);
        buttonB = findViewById(R.id.quizOption2);
        buttonC = findViewById(R.id.quizOption3);
        buttonD = findViewById(R.id.quizOption4);
        nextButton = findViewById(R.id.quizNextBtn);

        playSound = findViewById(R.id.quizPlaySound);

        buttonA.setOnClickListener(new AnswerClickListener());
        buttonB.setOnClickListener(new AnswerClickListener());
        buttonC.setOnClickListener(new AnswerClickListener());
        buttonD.setOnClickListener(new AnswerClickListener());
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizActivity.this, MainActivity.class));
            }
        });

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextToSpeech.speak(correctWord.getWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        ArrayList<Integer> list = new ArrayList<>(); // generate 4 unique random number to set position of buttons
        for (int i = 0; i < 4; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        buttonA.setText(optionsList.get(list.get(0)).toString());
        buttonB.setText(optionsList.get(list.get(1)).toString());
        buttonC.setText(optionsList.get(list.get(2)).toString());
        buttonD.setText(optionsList.get(list.get(3)).toString());

    }

    //This method will disable all the option button
    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    //This method will all enable the option buttons
    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    private class AnswerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            nextButton.setVisibility(View.VISIBLE);
            disableButton();
            if (button.getText().equals(correctWord.getWord())) {
                mWordViewModel.setNextWordPassed(correctWord.getWid());
                button.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green), PorterDuff.Mode.SRC_OVER);

                nextButton.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green), PorterDuff.Mode.SRC_OVER);
                nextButton.setTextColor(getResources().getColor(R.color.white));
            }
            else {
                Toast.makeText(QuizActivity.this, "آخ اشبتاه بود!", Toast.LENGTH_LONG).show();
                button.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), PorterDuff.Mode.SRC_OVER);
                nextButton.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), PorterDuff.Mode.SRC_OVER);
                nextButton.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }
}