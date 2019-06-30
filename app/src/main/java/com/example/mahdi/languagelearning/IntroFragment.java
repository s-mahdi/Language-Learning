package com.example.mahdi.languagelearning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class IntroFragment extends Fragment {
    private static final String ARG_LAYOUT_RESOURCE_ID = "layoutResId";

    private int layoutID;

    public IntroFragment() {
    }

    public static IntroFragment newInstance(int layoutID) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RESOURCE_ID, layoutID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RESOURCE_ID)) {
            layoutID = getArguments().getInt(ARG_LAYOUT_RESOURCE_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layoutID, container, false);
    }


}
