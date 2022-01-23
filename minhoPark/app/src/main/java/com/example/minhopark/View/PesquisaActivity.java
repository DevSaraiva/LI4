package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSParques.SSParquesFacade;


public class PesquisaActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa2);




        //maps






        //button




        Button myButton = new Button(this);
        myButton.setText("Push Me");

        LinearLayout ll = (LinearLayout)findViewById(R.id.btnLayout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(myButton, lp);


    }



}