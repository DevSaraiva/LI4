package com.example.minhopark.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSUtilizadores.Preferencia;



import java.util.Set;
import java.util.TreeSet;


public class PreferencesActivity extends AppCompatActivity {
    Preferencia p;
    RadioButton radioButtonLoc;
    RadioButton radioButtonNParques;
    RadioButton radioButtonPortagens;
    EditText outroEditText;
    String outroString;


    public void savePreferennces() {
        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("chaveLoc", p.getLoc());
        editor.putInt("chaveNParques", p.getnParques());
        editor.putBoolean("chavePortagens", p.getEvitarPortagens());
        editor.putStringSet("chaveListTiposParques", p.getTiposParques());

        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // bloco que le preferencias dum fichiero
        // FIXME nao resulta criar uma funcao auxiliar
        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        String loc = prefs.getString("chaveLoc", buscarInformacoesGPS());
        int nParques = prefs.getInt("chaveNParques", 10);
        boolean portagens = prefs.getBoolean("chavePortagens", false);
        Set<String> tipos = prefs.getStringSet("chaveListTiposParques", new TreeSet<>());
        p = new Preferencia(loc, nParques, portagens, tipos);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences_activity);


        RadioGroup radioGroupLoc = findViewById(R.id.radioGroupLoc);
        RadioGroup radioGroupNParques = findViewById(R.id.radioGroupNParques);
        RadioGroup radioGroupPortagens = findViewById(R.id.radioGroupEvitarPortagens);

        //parte que mete no inicio todos os butoes de acordo com as preferencias guardadas
        RadioButton locDispositivo = findViewById(R.id.radioButtonLocDoDispositivo);
        RadioButton outraLoc = findViewById(R.id.radioButtonOutraLoc);
        if (prefs.getBoolean("chaveBoolLocDispositivo",true)) {
            locDispositivo.setChecked(true);
        } else {
            outraLoc.setChecked(true);
            EditText editText = findViewById(R.id.editTextOutraLoc);
            editText.setText(p.getLoc());
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

                SharedPreferences.Editor editor = prefs.edit();

                switch (radioLocId) {
                    case R.id.radioButtonLocDoDispositivo:

                        String res = buscarInformacoesGPS();
                        if (res.equals("ERRO")) {
                            Toast.makeText(PreferencesActivity.this, "ERRO PERMISSOES", Toast.LENGTH_LONG).show();
                        } else if (res.equals("GPS DESABILITADO")) {
                            Toast.makeText(PreferencesActivity.this, "GPS DESABILITADO", Toast.LENGTH_LONG).show();
                        } else {
                            editor.putBoolean("chaveBoolLocDispositivo", true);
                            editor.commit();
                            p.setLoc(res);

                        }

                        break;

                    case (R.id.radioButtonOutraLoc):


                        outroEditText = findViewById(R.id.editTextOutraLoc);
                        outroString = outroEditText.getText().toString().trim();
                        if (verificaCoordenadas(outroString)){
                            editor.putBoolean("chaveBoolLocDispositivo", false);
                            editor.commit();
                            p.setLoc(outroString);

                        }else {
                            Toast.makeText(getApplicationContext(),"Coordenadas Inválidas", Toast.LENGTH_LONG).show();
                        }

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


                savePreferennces();
                Intent intent = new Intent(PreferencesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public String buscarInformacoesGPS() {
        String texto = "ERRO";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(PreferencesActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(PreferencesActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(PreferencesActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return texto;
        }

        LocationManager mlocManager = (LocationManager) getSystemService(PreferencesActivity.this.LOCATION_SERVICE);
        LocationListener mlocListener = new MinhaLocalizacaoListener();

        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            texto =  MinhaLocalizacaoListener.latitude + ","  + MinhaLocalizacaoListener.longitude ;

            //Toast.makeText(PreferencesActivity.this, texto, Toast.LENGTH_LONG).show();
        } else {
            //Toast.makeText(PreferencesActivity.this, "GPS DESABILITADO", Toast.LENGTH_LONG).show();
            texto = "GPS DESABILITADO";
        }

        return texto;

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean verificaCoordenadas(String coordenas){
        String[] parts = coordenas.split(",");
        if(parts.length!=2) {return false;}
        if(!isNumeric(parts[0]) || !isNumeric(parts[1])) {return false;}
        double latitude = Double.parseDouble(parts[0]);
        double longitude = Double.parseDouble(parts[1]);
        if(latitude>90 || latitude<-90) {return false;}
        if(longitude>180 || longitude<-180) {return false;}
        return true;
    }
}