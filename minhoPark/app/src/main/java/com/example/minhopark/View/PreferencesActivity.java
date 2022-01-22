package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.MinhoPark.R;


public class PreferencesActivity extends AppCompatActivity {
    Preferences p = new Preferences();
    RadioButton radioButtonLoc;
    RadioButton radioButtonNParques;
    RadioButton radioButtonPortagens;
    EditText outroEditText;
    String outroString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_activity);

        RadioGroup radioGroupLoc = findViewById(R.id.radioGroupLoc);
        RadioGroup radioGroupNParques = findViewById(R.id.radioGroupNParques);
        RadioGroup radioGroupPortagens = findViewById(R.id.radioGroupEvitarPortagens);

        RadioButton locDispositivo = findViewById(R.id.radioButtonLocDoDispositivo);
        RadioButton outraLoc = findViewById(R.id.radioButtonOutraLoc);
        if (p.getLoc().equals("LocDispositivo")) {
            locDispositivo.setChecked(true);
        }

        if (p.getLoc().equals("LocOutro")) {
            outraLoc.setChecked(true);
        }

        RadioButton parques5 = findViewById(R.id.radioButton5Parques);
        RadioButton parques10 = findViewById(R.id.radioButton10Parques);
        RadioButton parques15 = findViewById(R.id.radioButton15Parques);

        if (p.getnParques() == 5) {
            parques5.setChecked(true);
        } else if (p.getnParques() == 10) {
            parques10.setChecked(true);
        } else if (p.getnParques() == 15) {
            parques15.setChecked(true);
        }

        RadioButton evitarPortSim = findViewById(R.id.radioButtonEvitarPortaSim);
        RadioButton evitarPortNao = findViewById(R.id.radioButtonEvitarPortaNao);

        if (p.getEvitarPortagens())
            evitarPortSim.setChecked(true);
        else
            evitarPortNao.setChecked(true);

        Switch switchParqueLazer = findViewById(R.id.switchParqueLazer);
        Switch switchParqueUrbano = findViewById(R.id.switchParqueUrbano);
        Switch switchParqueInfantil = findViewById(R.id.switchParqueInfatil);
        Switch switchParqueCampismo = findViewById(R.id.switchParqueCampismo);
        Switch switchParqueDiversoes = findViewById(R.id.switchParqueDiversoes);
        Switch switchParquePatinagem = findViewById(R.id.switchParquePatinagem);
        Switch switchParqueEcologico = findViewById(R.id.switchParqueEcologico);
        Switch switchParqueCidade = findViewById(R.id.switchParqueCidade);
        Switch switchParqueEstatal = findViewById(R.id.switchParqueEstatal);
        Switch switchParqueNacional = findViewById(R.id.switchParqueNacional);
        Switch switchJardim = findViewById(R.id.switchJardim);
        Switch switchReservaNatural = findViewById(R.id.switchParqueNatural);

        if (this.p.getTiposParques().contains("Parque_De_Lazer"))
            switchParqueLazer.setChecked(true);
        else
            switchParqueLazer.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Urbano"))
            switchParqueUrbano.setChecked(true);
        else
            switchParqueUrbano.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Infatil"))
            switchParqueInfantil.setChecked(true);
        else
            switchParqueInfantil.setChecked(false);

        if (this.p.getTiposParques().contains("Parque_Campismo"))
            switchParqueCampismo.setChecked(true);
        else
            switchParqueCampismo.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Diversoes"))
            switchParqueDiversoes.setChecked(true);
        else
            switchParqueDiversoes.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Patinagem"))
            switchParquePatinagem.setChecked(true);
        else
            switchParquePatinagem.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Ecologico"))
            switchParqueEcologico.setChecked(true);
        else
            switchParqueEcologico.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Cidade"))
            switchParqueCidade.setChecked(true);
        else
            switchParqueCidade.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Estatal"))
            switchParqueEstatal.setChecked(true);
        else
            switchParqueEstatal.setChecked(false);
        if (this.p.getTiposParques().contains("Parque_Nacional"))
            switchParqueNacional.setChecked(true);
        else
            switchParqueNacional.setChecked(false);
        if (this.p.getTiposParques().contains("Jardim"))
            switchJardim.setChecked(true);
        else
            switchJardim.setChecked(false);
        if (this.p.getTiposParques().contains("Reserva_Natural"))
            switchReservaNatural.setChecked(true);
        else
            switchReservaNatural.setChecked(false);


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
                        // TODO - buscar locDispositivo
                        p.setLoc("LocDispositivo");
                        break;

                    case (R.id.radioButtonOutraLoc):
                        outroEditText = findViewById(R.id.editTextTextPersonName);
                        outroString = outroEditText.getText().toString().trim();
                        Toast.makeText(getApplicationContext(), outroString, Toast.LENGTH_LONG).show(); //TODO tirar este TOAST e passar para a BD
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



                if (switchParqueLazer.isChecked()) p.addParque("Parque_De_Lazer");
                else p.removeParque("Parque_De_Lazer");

                if (switchParqueUrbano.isChecked()) p.addParque("Parque_Urbano");
                else p.removeParque("Parque_Urbano");

                if (switchParqueInfantil.isChecked()) p.addParque("Parque_Infatil");
                else p.removeParque("Parque_Infatil");

                if (switchParqueCampismo.isChecked()) p.addParque("Parque_Campismo");
                else p.removeParque("Parque_Campismo");

                if (switchParqueDiversoes.isChecked()) p.addParque("Parque_Diversoes");
                else p.removeParque("Parque_Diversoes");

                if (switchParquePatinagem.isChecked()) p.addParque("Parque_Patinagem");
                else p.removeParque("Parque_Patinagem");

                if (switchParqueEcologico.isChecked()) p.addParque("Parque_Ecologico");
                else p.removeParque("Parque_Ecologico");

                if (switchParqueCidade.isChecked()) p.addParque("Parque_Cidade");
                else p.removeParque("Parque_Cidade");

                if (switchParqueEstatal.isChecked()) p.addParque("Parque_Estatal");
                else p.removeParque("Parque_Estatal");

                if (switchParqueNacional.isChecked()) p.addParque("Parque_Nacional");
                else p.removeParque("Parque_Nacional");

                if (switchJardim.isChecked()) p.addParque("Jardim");
                else p.removeParque("Jardim");

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