package com.example.minhopark.View;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSParques.Parque;
import com.example.minhopark.model.SSParques.SSParquesFacade;
import com.example.minhopark.model.SSUtilizadores.Preferencia;
import com.example.minhopark.View.DetalhesParque;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FavouritesActivity extends AppCompatActivity {
    Set<Integer> favoritos;
    Set<String> favoritosString;
    Set<String> favoritosGravar;


    private class Connect extends AsyncTask<Void, Void, Parque> {


        Preferencia preferencia;
        Set<Integer> favoritos;
        int id;


        public  Connect(Preferencia p , Set<Integer> favoritos, int id){
            this.preferencia = p;
            this.favoritos = favoritos;
            this.id = id;
        }

        @Override
        protected Parque doInBackground(Void ...params) {

            SSParquesFacade ssParquesFacade = new SSParquesFacade(preferencia,favoritos);

            Parque p = ssParquesFacade.getParque(id);

            return p;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);



        //le preferencias
        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        String loc = prefs.getString("chaveLoc", buscarInformacoesGPS());
        int nParques = prefs.getInt("chaveNParques", 10);
        boolean portagens = prefs.getBoolean("chavePortagens", false);
        Set<String> tipos = prefs.getStringSet("chaveListTiposParques", new TreeSet<>());
        Preferencia p = new Preferencia(loc, nParques, portagens, tipos);


       //le Favoritos
        Set<String> favoritosString = prefs.getStringSet("chaveFavoritos", new TreeSet<>());
        Set<Integer> favoritos = new TreeSet<>();
        for (String s : favoritosString) {
            favoritos.add(Integer.parseInt(s));
        }




       LinearLayout layer= (LinearLayout) findViewById(R.id.btnLayout);


        for (Integer i : favoritos) {
            LinearLayout parque_layout= new LinearLayout(this);
            parque_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));
            parque_layout.setOrientation(LinearLayout.HORIZONTAL);

            //Get Parque

            Parque parque = null;
            Connect task = new Connect(p,favoritos,i);
            try {
                parque = task.execute().get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(550,LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setText("Favorito: " + parque.getNome() + "\n" +
                      "Localização: " + parque.getEndereco() + "\n\n");


            ImageButton buttonFav = new ImageButton(this);
            buttonFav.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonFav.setId(i);
            buttonFav.setImageResource(R.drawable.custom_button_fav);
            buttonFav.setActivated(true);
            buttonFav.setX(150);

            buttonFav.setOnClickListener((a) -> {
                if (buttonFav.isActivated()){
                    favoritos.remove(i);
                    buttonFav.setActivated(false);
                } else {
                    favoritos.add(i);
                    buttonFav.setActivated(true);
                }

            });

            parque_layout.addView(tv);
            parque_layout.addView(buttonFav);
            layer.addView(parque_layout);
        }


        Button buttonBackFavs = findViewById(R.id.buttonBackFavs);
        buttonBackFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // grava toda a vez que clica na estrela secalhar meter só a gravar quando sai deste menu (Falta botao voltar atras)
                Set<String> favoritosGravar = new TreeSet<>();
                for (Integer x : favoritos) {
                    favoritosGravar.add(x.toString());
                }
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putStringSet("chaveFavoritos", favoritosGravar);
                editor.commit();
                Intent intent = new Intent(FavouritesActivity.this, MainActivity.class);    //@click go back to MainActivity (initial menu)
                startActivity(intent);
            }
        });

    }


    public String buscarInformacoesGPS() {
        String texto = "ERRO";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(FavouritesActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(FavouritesActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(FavouritesActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return texto;
        }

        LocationManager mlocManager = (LocationManager) getSystemService(FavouritesActivity.this.LOCATION_SERVICE);
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

}