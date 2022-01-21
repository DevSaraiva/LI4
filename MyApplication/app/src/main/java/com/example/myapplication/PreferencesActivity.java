package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity {
    Preferences p;
    RadioButton radioButtonLoc;
    RadioButton radioButtonNParques;
    RadioButton radioButtonPortagens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p = new Preferences();
        setContentView(R.layout.activity_preferences_activity);

        RadioGroup radioGroupLoc = findViewById(R.id.radioGroupLoc);
        RadioGroup radioGroupNParques = findViewById(R.id.radioGroupNParques);
        RadioGroup radioGroupPortagens = findViewById(R.id.radioGroupEvitarPortagens);


        Button buttonExit = findViewById(R.id.buttonBack);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioLocId = radioGroupLoc.getCheckedRadioButtonId();
                radioButtonLoc = findViewById(radioLocId);

                int radioNParquesId = radioGroupNParques.getCheckedRadioButtonId();
                radioButtonNParques = findViewById(radioNParquesId);

                int radioPortId = radioGroupPortagens.getCheckedRadioButtonId();
                radioButtonPortagens = findViewById(radioPortId);



                switch (radioLocId){
                    case R.id.radioButtonLocDoDispositivo:
                        p.setLoc("LocDispositivo");
                        break;

                    case R.id.radioButtonOutraLoc:
                        p.setLoc("LocOutra");
                        break;
                }

                switch (radioNParquesId) {
                    case R.id.radioButton5Parques:
                        p.setnParques(5);
                        break;

                    case R.id.radioButton10Parques:
                        p.setnParques(10);
                        break;

                    case R.id.radioButton15Parques:
                        p.setnParques(15);
                        break;
                }

                switch (radioPortId) {
                    case R.id.radioButtonEvitarPortaSim:
                        p.setEvitarPortagens(true);
                        break;

                    case R.id.radioButtonEvitarPortaNao:
                        p.setEvitarPortagens(false);
                        break;
                }


                Intent intent = new Intent(PreferencesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        /*Button buttonAplly = findViewById(R.id.buttonApply);
        buttonAplly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioLocId = radioGroupLoc.getCheckedRadioButtonId();
                radioButtonLoc = findViewById(radioLocId);

                int radioNParquesId = radioGroupNParques.getCheckedRadioButtonId();
                radioButtonNParques = findViewById(radioNParquesId);

                int radioPortId = radioGroupPortagens.getCheckedRadioButtonId();
                radioButtonPortagens = findViewById(radioPortId);



                switch (radioLocId){
                    case R.id.radioButtonLocDoDispositivo:
                        p.setLoc("LocDispositivo");
                        break;

                    case R.id.radioButtonOutraLoc:
                        p.setLoc("LocOutra");
                        break;
                }

                switch (radioNParquesId) {
                    case R.id.radioButton5Parques:
                        p.setnParques(5);
                        break;

                    case R.id.radioButton10Parques:
                        p.setnParques(10);
                        break;

                    case R.id.radioButton15Parques:
                        p.setnParques(15);
                        break;
                }

                switch (radioPortId) {
                    case R.id.radioButtonEvitarPortaSim:
                        //p.setEvitarPortagens(true);
                        break;

                    case R.id.radioButtonEvitarPortaNao:
                        //p.setEvitarPortagens(false);
                        break;
                }


            }
        });*/





    }


}