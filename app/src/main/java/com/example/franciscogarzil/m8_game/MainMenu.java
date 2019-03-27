package com.example.franciscogarzil.m8_game;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.navigation.Navigation;

public class MainMenu extends Fragment implements View.OnClickListener {

    public MainMenu() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu, container, false);
        ImageButton continueBtn = v.findViewById(R.id.btn_continue);
        Button newCharacterButton = v.findViewById(R.id.createCharacterBtn);
        continueBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.characterPage, null));
        newCharacterButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createCharacterBtn:

                break;
        }
    }
}
