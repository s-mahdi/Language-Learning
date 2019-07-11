package com.example.mahdi.languagelearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro2 {

    Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         i = new Intent(IntroActivity.this, MainActivity.class);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("آموزش زبان");
        sliderPage1.setDescription("بیا با هم حرف‌های زبان انگلیسی رو یاد بگیریم");
        sliderPage1.setImageDrawable(R.drawable.ic_english);
        sliderPage1.setBgColor(getResources().getColor(R.color.blue));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("بازی");
        sliderPage2.setDescription("اینجا میتونی بازی کنی و لذت ببری");
         sliderPage2.setImageDrawable(R.drawable.ic_football);
        sliderPage2.setBgColor(getResources().getColor(R.color.red));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("محیط شاد");
        sliderPage3.setDescription("کلی مرحله داریم");
        sliderPage3.setImageDrawable(R.drawable.ic_smile);
        sliderPage3.setBgColor(getResources().getColor(R.color.purple));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(i);
        finish();
    }


    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(i);
        finish();
    }
}
