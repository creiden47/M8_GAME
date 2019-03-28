package com.example.franciscogarzil.m8_game;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        getSupportActionBar().hide();
        try {
            characters();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void characters() throws ClassNotFoundException {
        LinearLayout Llayout = (LinearLayout) findViewById(R.id.parent_list);

        Button createCharacterBtn = (Button) findViewById(R.id.createCharacterBtn);

        ArrayList<Character> character = ConnectorSQL.getListCharacters("Bernat");
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

            TextView tv = new TextView(this);
            relLayout.addView(tv);
            tv.setTextColor(getResources().getColor(R.color.colorTextLight)); //TextColor
            tv.setTextSize(18); //TextSize
            tv.setText(character.get(i).get_name()); //Text
            tv.setPadding(15, 0,0,0);

            LayoutParams tv_params = (LayoutParams) tv.getLayoutParams();
            tv_params.width = LayoutParams.WRAP_CONTENT; //Width
            tv_params.height = LayoutParams.WRAP_CONTENT; //Height

            MarginLayoutParams tv_marginLayoutParams = (MarginLayoutParams) tv.getLayoutParams();
            tv_marginLayoutParams.setMargins(16,0,0,0); //Margins

            RelativeLayout.LayoutParams tv_relLayoutParams =(RelativeLayout.LayoutParams)tv.getLayoutParams();
            tv_relLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.ALIGN_PARENT_START);
            tv_relLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            tv_relLayoutParams.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);

            tv.setLayoutParams(tv_params);
            tv.setLayoutParams(tv_marginLayoutParams);
            tv.setLayoutParams(tv_relLayoutParams);


            ImageButton btn = new ImageButton(this);
            relLayout.addView(btn);
            btn.setImageResource(R.drawable.play_icon);
            btn.setBackgroundColor(Color.parseColor("#0F0F0F"));
            btn.setPadding(15, 15,15,15);

            LayoutParams btn_params = btn.getLayoutParams();
            btn_params.width = 90; //Width
            btn_params.height = 90; //Height

            LinearLayout.LayoutParams btn_gravityParams = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            btn_gravityParams.gravity = Gravity.END;

            MarginLayoutParams btn_marginLayoutParams = (MarginLayoutParams) tv.getLayoutParams();
            btn_marginLayoutParams.setMargins(5,5,5,5); //Margins

            RelativeLayout.LayoutParams btn_LayoutParams =(RelativeLayout.LayoutParams)btn.getLayoutParams();
            btn_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_END);

            btn.setLayoutParams(btn_params);
            btn.setLayoutParams(btn_gravityParams);
            btn.setLayoutParams(btn_marginLayoutParams);
            btn.setLayoutParams(btn_LayoutParams);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //Navigation.createNavigateOnClickListener(R.id.characterPage, null)
                }
            });

        }
    }
}
