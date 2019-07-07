package com.example.mahdi.languagelearning;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mahdi.languagelearning.RoomDB.Word;

import java.util.Locale;
import java.util.Objects;

public class TrainingActivity extends AppCompatActivity {

    public static final String EXTRA_WORD = "com.languageLearning.trainingActivity.word";

    TextView wordTV;

    View card1;
    View card2;

    ImageButton cardImageButton1;
    TextView cardTextView1;
    ImageButton cardImageButton2;
    TextView cardTextView2;

    Word word;
    String wordText;

    Button nextButton;

    TextToSpeech mTextToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Objects.requireNonNull(getSupportActionBar()).hide();

        getView();

        mTextToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    mTextToSpeech.setLanguage(Locale.US);
                }
            }
        });

        word = getIntent().getParcelableExtra(MainActivity.EXTRA_REPLY);

        wordText = word.getWord();

        wordTV.setText(wordText);

        setCard(wordText);

        wordTV.setOnClickListener(new cardClickListener(wordText));

        cardImageButton1.setOnClickListener(new cardClickListener(cardTextView1.getText().toString()));
        cardImageButton2.setOnClickListener(new cardClickListener(cardTextView2.getText().toString()));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TrainingActivity.this, QuizActivity.class);
                i.putExtra(EXTRA_WORD, word);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onPause() {

        if (mTextToSpeech != null) {
            mTextToSpeech.stop();
            mTextToSpeech.shutdown();
        }
        super.onPause();
    }

    private void setCard(String word) {
        switch (word) {
            case "A":
                cardImageButton1.setImageResource(R.drawable.ic_ant);// card 1
                cardTextView1.setText(R.string.ant);

                cardImageButton2.setImageResource(R.drawable.ic_apple);//card 2
                cardTextView2.setText(R.string.apple);
                break;


            case "B":
                cardImageButton1.setImageResource(R.drawable.ic_bag);// card 1
                cardTextView1.setText(R.string.bag);

                cardImageButton2.setImageResource(R.drawable.ic_ball);//card 2
                cardTextView2.setText(R.string.ball);
                break;


            case "C":
                cardImageButton1.setImageResource(R.drawable.ic_cake);// card 1
                cardTextView1.setText(R.string.cake);

                cardImageButton2.setImageResource(R.drawable.ic_cat);//card 2
                cardTextView2.setText(R.string.cat);
                break;


            case "D":
                cardImageButton1.setImageResource(R.drawable.ic_dog);// card 1
                cardTextView1.setText(R.string.dog);

                cardImageButton2.setImageResource(R.drawable.ic_door);//card 2
                cardTextView2.setText(R.string.door);
                break;


            case "E":
                cardImageButton1.setImageResource(R.drawable.ic_eggs);// card 1
                cardTextView1.setText(R.string.egg);

                cardImageButton2.setImageResource(R.drawable.ic_elephant);//card 2
                cardTextView2.setText(R.string.elephant);
                break;


            case "F":
                cardImageButton1.setImageResource(R.drawable.ic_fish);// card 1
                cardTextView1.setText(R.string.fish);

                cardImageButton2.setImageResource(R.drawable.ic_flower);//card 2
                cardTextView2.setText(R.string.flower);
                break;


            case "G":
                cardImageButton1.setImageResource(R.drawable.ic_gift);// card 1
                cardTextView1.setText(R.string.gift);

                cardImageButton2.setImageResource(R.drawable.ic_grass);//card 2
                cardTextView2.setText(R.string.grass);
                break;


            case "H":
                cardImageButton1.setImageResource(R.drawable.ic_hat);// card 1
                cardTextView1.setText(R.string.hat);

                cardImageButton2.setImageResource(R.drawable.ic_hourse);//card 2
                cardTextView2.setText(R.string.horse);
                break;


            case "I":
                cardImageButton1.setImageResource(R.drawable.ic_ice_cream);// card 1
                cardTextView1.setText(R.string.ice_cream);

                cardImageButton2.setImageResource(R.drawable.ic_iran);//card 2
                cardTextView2.setText(R.string.iran);
                break;


            case "J":
                cardImageButton1.setImageResource(R.drawable.ic_jam);// card 1
                cardTextView1.setText(R.string.jam);

                cardImageButton2.setImageResource(R.drawable.ic_jellyfish);//card 2
                cardTextView2.setText(R.string.jellyfish);
                break;


            case "K":
                cardImageButton1.setImageResource(R.drawable.ic_kangaroo);// card 1
                cardTextView1.setText(R.string.kangaroo);

                cardImageButton2.setImageResource(R.drawable.ic_baby);//card 2
                cardTextView2.setText(R.string.kid);
                break;


            case "L":
                cardImageButton1.setImageResource(R.drawable.ic_lamp);// card 1
                cardTextView1.setText(R.string.lamp);

                cardImageButton2.setImageResource(R.drawable.ic_lion);//card 2
                cardTextView2.setText(R.string.lion);
                break;


            case "M":
                cardImageButton1.setImageResource(R.drawable.ic_milk);// card 1
                cardTextView1.setText(R.string.milk);

                cardImageButton2.setImageResource(R.drawable.ic_mother);//card 2
                cardTextView2.setText(R.string.mom);
                break;


            case "N":
                cardImageButton1.setImageResource(R.drawable.ic_moon);// card 1
                cardTextView1.setText(R.string.night);

                cardImageButton2.setImageResource(R.drawable.ic_nose);//card 2
                cardTextView2.setText(R.string.nose);
                break;


            case "O":
                cardImageButton1.setImageResource(R.drawable.ic_octopus);// card 1
                cardTextView1.setText(R.string.octopus);

                cardImageButton2.setImageResource(R.drawable.ic_orange);//card 2
                cardTextView2.setText(R.string.orange);
                break;


            case "P":
                cardImageButton1.setImageResource(R.drawable.ic_pizza);// card 1
                cardTextView1.setText(R.string.pizza);

                cardImageButton2.setImageResource(R.drawable.ic_purple);//card 2
                cardTextView2.setText(R.string.purple);
                break;


            case "Q":
                cardImageButton1.setImageResource(R.drawable.ic_queen);// card 1
                cardTextView1.setText(R.string.queen);

                cardImageButton2.setImageResource(R.drawable.ic_quick);//card 2
                cardTextView2.setText(R.string.quick);
                break;


            case "R":
                cardImageButton1.setImageResource(R.drawable.ic_rabbit);// card 1
                cardTextView1.setText(R.string.rabbit);

                cardImageButton2.setImageResource(R.drawable.ic_rain);//card 2
                cardTextView2.setText(R.string.rain);
                break;


            case "S":
                cardImageButton1.setImageResource(R.drawable.ic_wave);// card 1
                cardTextView1.setText(R.string.sea);

                cardImageButton2.setImageResource(R.drawable.ic_sun);//card 2
                cardTextView2.setText(R.string.sun);
                break;


            case "T":
                cardImageButton1.setImageResource(R.drawable.ic_desk);// card 1
                cardTextView1.setText(R.string.table);

                cardImageButton2.setImageResource(R.drawable.ic_toy);//card 2
                cardTextView2.setText(R.string.toy);
                break;


            case "U":
                cardImageButton1.setImageResource(R.drawable.ic_umbrella);// card 1
                cardTextView1.setText(R.string.umbrella);

                cardImageButton2.setImageResource(R.drawable.ic_up);//card 2
                cardTextView2.setText(R.string.up);
                break;


            case "V":
                cardImageButton1.setImageResource(R.drawable.ic_van);// card 1
                cardTextView1.setText(R.string.van);

                cardImageButton2.setImageResource(R.drawable.ic_vegetable);//card 2
                cardTextView2.setText(R.string.vegetable);
                break;


            case "W":
                cardImageButton1.setImageResource(R.drawable.ic_wolf);// card 1
                cardTextView1.setText(R.string.wolf);

                cardImageButton2.setImageResource(R.drawable.ic_tree);//card 2
                cardTextView2.setText(R.string.wood);
                break;


            case "X":
                cardImageButton1.setImageResource(R.drawable.ic_box);// card 1
                cardTextView1.setText(R.string.box);

                cardImageButton2.setImageResource(R.drawable.ic_fox);//card 2
                cardTextView2.setText(R.string.fox);
                break;


            case "Y":
                cardImageButton1.setImageResource(R.drawable.ic_toy);// card 1
                cardTextView1.setText(R.string.toy);

                cardImageButton2.setImageResource(R.drawable.ic_boy);//card 2
                cardTextView2.setText(R.string.boy);
                break;


            case "Z":
                cardImageButton1.setImageResource(R.drawable.ic_zebra);// card 1
                cardTextView1.setText(R.string.zebra);

                cardImageButton2.setImageResource(R.drawable.ic_zip);//card 2
                cardTextView2.setText(R.string.zip);
                break;
        }
    }

    private void getView() {
        wordTV = findViewById(R.id.TrainingWordTextView);

        card1 = findViewById(R.id.training_card_1);
        card2 = findViewById(R.id.training_card_2);

        cardImageButton1 = card1.findViewById(R.id.trainingCardIB);
        cardTextView1 = card1.findViewById(R.id.trainingCardTV);

        cardImageButton2 = card2.findViewById(R.id.trainingCardIB);
        cardTextView2 = card2.findViewById(R.id.trainingCardTV);

        nextButton = findViewById(R.id.quizNextBtn);
    }

    class cardClickListener implements View.OnClickListener {

        String textToSpeech;

        cardClickListener(String textToSpeech) {
            this.textToSpeech = textToSpeech;
        }

        @Override
        public void onClick(View view) {
            mTextToSpeech.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

}