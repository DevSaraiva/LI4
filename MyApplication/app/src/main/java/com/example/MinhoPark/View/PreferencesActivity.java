package com.example.MinhoPark.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.MinhoPark.Preferences;
import com.example.MinhoPark.R;

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
                        // TODO - buscar ao campo de texto
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


                Switch switchParqueLazer = findViewById(R.id.switchParqueLazer);
                if (switchParqueLazer.isChecked()) p.addParque("Parque_De_Lazer");
                else p.removeParque("Parque_De_Lazer");

                Switch switchParqueUrbano = findViewById(R.id.switchParqueUrbano);
                if (switchParqueUrbano.isChecked()) p.addParque("Parque_Urbano");
                else p.removeParque("Parque_Urbano");

                Switch switchParqueInfatil = findViewById(R.id.switchParqueInfatil);
                if (switchParqueInfatil.isChecked()) p.addParque("Parque_Infatil");
                else p.removeParque("Parque_Infatil");

                Switch switchParqueCampismo = findViewById(R.id.switchParqueCampismo);
                if (switchParqueCampismo.isChecked()) p.addParque("Parque_Campismo");
                else p.removeParque("Parque_Campismo");

                Switch switchParqueDiversoes = findViewById(R.id.switchParqueDiversoes);
                if (switchParqueDiversoes.isChecked()) p.addParque("Parque_Diversoes");
                else p.removeParque("Parque_Diversoes");

                Switch switchParquePatinagem = findViewById(R.id.switchParquePatinagem);
                if (switchParquePatinagem.isChecked()) p.addParque("Parque_Patinagem");
                else p.removeParque("Parque_Patinagem");

                Switch switchParqueEcologico = findViewById(R.id.switchParqueEcologico);
                if (switchParqueEcologico.isChecked()) p.addParque("Parque_Ecologico");
                else p.removeParque("Parque_Ecologico");

                Switch switchParqueCidade = findViewById(R.id.switchParqueCidade);
                if (switchParqueCidade.isChecked()) p.addParque("Parque_Cidade");
                else p.removeParque("Parque_Cidade");

                Switch switchParqueEstatal = findViewById(R.id.switchParqueEstatal);
                if (switchParqueEstatal.isChecked()) p.addParque("Parque_Estatal");
                else p.removeParque("Parque_Estatal");

                Switch switchParqueNacional = findViewById(R.id.switchParqueNacional);
                if (switchParqueNacional.isChecked()) p.addParque("Parque_Nacional");
                else p.removeParque("Parque_Nacional");

                Switch switchJardim = findViewById(R.id.switchJardim);
                if (switchJardim.isChecked()) p.addParque("Jardim");
                else p.removeParque("Jardim");

                Switch switchReservaNatural = findViewById(R.id.switchParqueNatural);
                if (switchReservaNatural.isChecked()) p.addParque("Reserva_Natural");
                else p.removeParque("Reserva_Natural");

                Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
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