package com.example.mahdi.languagelearning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahdi.languagelearning.RoomDB.Word;
import com.example.mahdi.languagelearning.RoomDB.WordViewModel;
import com.example.mahdi.languagelearning.recyclerView.RecyclerViewClickListener;
import com.example.mahdi.languagelearning.recyclerView.WordListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_REPLY = "om.example.android.languagelearning.mainActivity.REPLY";

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if user enter app for first time then show intro activity
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.languageLearning", Context.MODE_PRIVATE);

        if (!Objects.equals(sharedPreferences.getString("isLogedIn", String.valueOf(false)), "true")) {
            sharedPreferences.edit().putString("isLogedIn", "true").apply();
            startActivity(new Intent(MainActivity.this, IntroActivity.class));
        }

        Toolbar toolbar = findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("صفحه اصلی");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("پروفایل");

        new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.util_drawer_hdr)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    // TODO: 12/07/2019 actions of drawer should be done here

                            }

                        }

                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(false)
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .withDrawerGravity(Gravity.END)
                .build();

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.mainActivityRecyclerView);

        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Word clickedWord = Objects.requireNonNull(mWordViewModel.getAllWords().getValue()).get(position);
                if (clickedWord.isPassed()) {
                    Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                    intent.putExtra(EXTRA_REPLY, clickedWord);
                    startActivity(intent);
                } else Toast.makeText(MainActivity.this, "هنوز باز نشده!", Toast.LENGTH_LONG).show();


                /*Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_LONG).show();
                Word clickedWord = Objects.requireNonNull(mWordViewModel.getAllWords().getValue()).get(position);
                mWordViewModel.setPassed(clickedWord);*/
            }
        };

        final WordListAdapter adapter = new WordListAdapter(this, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY),
                    data.getBooleanExtra(NewWordActivity.EXTRA_REPLY_PASSED, false));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
