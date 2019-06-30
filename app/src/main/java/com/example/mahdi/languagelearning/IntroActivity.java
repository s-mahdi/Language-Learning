package com.example.mahdi.languagelearning;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.github.paolorotolo.appintro.AppIntro;

import java.util.Objects;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addSlide(IntroFragment.newInstance(R.layout.fragment_intro));

    }
}
