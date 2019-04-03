package com.example.franciscogarzil.m8_game.PKG_ACTIVITIES;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

import com.example.franciscogarzil.m8_game.PKG_CLASS.Character;
import com.example.franciscogarzil.m8_game.PKG_CONNECTION.ConnectorSQL;
import com.example.franciscogarzil.m8_game.R;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    public static Character currentCharacter;
    private ArrayList<TextView> arrayListTv = new ArrayList<TextView>();
    private ArrayList<ImageButton> arrayListButtons = new ArrayList<ImageButton>();
    private ArrayList<Character> character;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        try {
            characters();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void characters() throws ClassNotFoundException {
        LinearLayout Llayout = (LinearLayout) findViewById(R.id.parent_list);

        Button createCharacterBtn = (Button) findViewById(R.id.createCharacterBtn);

        character = ConnectorSQL.getListCharacters(Login.currentUserName);
        for (int i = 0; i < character.size(); i++) {
            TextView tv = new TextView(this);
            ImageButton btn = new ImageButton(this);
            btn.setId(i);
            arrayListTv.add(tv);
            arrayListButtons.add(btn);
        }

        for(int i = 0; i < character.size(); i++){
            RelativeLayout relLayout = new RelativeLayout(this);
            Llayout.addView(relLayout);
            relLayout.setBackgroundResource(R.drawable.card_background); //Background

            LayoutParams relativeLayoutsParams = relLayout.getLayoutParams();
            relativeLayoutsParams.width = LayoutParams.WRAP_CONTENT; //Width
            relativeLayoutsParams.height = LayoutParams.WRAP_CONTENT; //Height

            relLayout.setLayoutParams(relativeLayoutsParams);

            if (i == 0){
                MarginLayoutParams rel_marginLayoutParams = (MarginLayoutParams) relLayout.getLayoutParams();
                rel_marginLayoutParams.setMargins(10,100,10,10); //Margins
                relLayout.setLayoutParams(rel_marginLayoutParams);
            } else {
                MarginLayoutParams rel_marginLayoutParams = (MarginLayoutParams) relLayout.getLayoutParams();
                rel_marginLayoutParams.setMargins(10,10,10,10); //Margins
                relLayout.setLayoutParams(rel_marginLayoutParams);
            }



            relLayout.addView(arrayListTv.get(i));
            arrayListTv.get(i).setTextColor(getResources().getColor(R.color.colorTextLight)); //TextColor
            arrayListTv.get(i).setTextSize(18); //TextSize
            arrayListTv.get(i).setText(character.get(i).get_name()); //Text
            arrayListTv.get(i).setPadding(15, 0,0,0);

            LayoutParams tv_params = (LayoutParams) arrayListTv.get(i).getLayoutParams();
            tv_params.width = LayoutParams.WRAP_CONTENT; //Width
            tv_params.height = LayoutParams.WRAP_CONTENT; //Height

            MarginLayoutParams tv_marginLayoutParams = (MarginLayoutParams) arrayListTv.get(i).getLayoutParams();
            tv_marginLayoutParams.setMargins(16,0,0,0); //Margins

            RelativeLayout.LayoutParams tv_relLayoutParams =(RelativeLayout.LayoutParams)arrayListTv.get(i).getLayoutParams();
            tv_relLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_START);
            tv_relLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            tv_relLayoutParams.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);

            arrayListTv.get(i).setLayoutParams(tv_params);
            arrayListTv.get(i).setLayoutParams(tv_marginLayoutParams);
            arrayListTv.get(i).setLayoutParams(tv_relLayoutParams);


            relLayout.addView(arrayListButtons.get(i));
            arrayListButtons.get(i).setImageResource(R.drawable.play_icon);
            arrayListButtons.get(i).setBackgroundColor(Color.parseColor("#0F0F0F"));
            arrayListButtons.get(i).setPadding(15, 15,15,15);

            LayoutParams btn_params = arrayListButtons.get(i).getLayoutParams();
            btn_params.width = 90; //Width
            btn_params.height = 90; //Height

            LinearLayout.LayoutParams btn_gravityParams = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            btn_gravityParams.gravity = Gravity.END;

            MarginLayoutParams btn_marginLayoutParams = (MarginLayoutParams) arrayListButtons.get(i).getLayoutParams();
            btn_marginLayoutParams.setMargins(5,5,5,5); //Margins

            RelativeLayout.LayoutParams btn_LayoutParams =(RelativeLayout.LayoutParams)arrayListButtons.get(i).getLayoutParams();
            btn_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_END);

            arrayListButtons.get(i).setLayoutParams(btn_params);
            arrayListButtons.get(i).setLayoutParams(btn_gravityParams);
            arrayListButtons.get(i).setLayoutParams(btn_marginLayoutParams);
            arrayListButtons.get(i).setLayoutParams(btn_LayoutParams);

            arrayListButtons.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    ImageButton b = (ImageButton)arg0;
                    for (int i = 0; i < arrayListButtons.size(); i++){
                        if (i == b.getId()){
                            currentCharacter = character.get(i);
                            Intent myIntent = new Intent(MainMenu.this, CharacterPage.class);
                            MainMenu.this.startActivity(myIntent);
                        }
                    }

                }
            });

        }
    }
    @Override
    public void onBackPressed () {

    }

    public void newCharacterEvent(View view) {
        Intent myIntent = new Intent(MainMenu.this, NewCharacter.class);
        MainMenu.this.startActivity(myIntent);
    }
}
