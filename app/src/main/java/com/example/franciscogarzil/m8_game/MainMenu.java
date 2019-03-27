package com.example.franciscogarzil.m8_game;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.navigation.Navigation;

public class MainMenu extends Fragment implements View.OnClickListener {
    LinearLayout lt1;

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

    private void references(View v){
        lt1 = (LinearLayout) v.findViewById(R.id.parent_list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createCharacterBtn:

                break;
        }
    }

    public void characters(View v) throws ClassNotFoundException {
        LinearLayout Llayout = (LinearLayout) v.findViewById(R.id.parent_list);
        Button createCharacterBtn = (Button) v.findViewById(R.id.createCharacterBtn);
        ArrayList<Character> character = ConnectorSQL.getListCharacters("Bernat");
        for(int i = 0; i == character.size(); i++){
            Button btn = createCharacterBtn;
            Llayout.addView(btn);
            btn.setText(character.get(i).get_name());

        }
    }
}
