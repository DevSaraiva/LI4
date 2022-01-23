package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.MinhoPark.R;



public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonPref = findViewById(R.id.buttonPreferencias);
        Button buttonFav = findViewById(R.id.buttonFavoritos);
        Button butonProc = findViewById(R.id.buttonProcura);

        buttonPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarInformacoesGPS(view);
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });

        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(intent);
            }
        });
    }


    public void buscarInformacoesGPS(View v) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return;
        }

        LocationManager mlocManager = (LocationManager) getSystemService(MainActivity.this.LOCATION_SERVICE);
        LocationListener mlocListener = new MinhaLocalizacaoListener();

        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

        if (mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String texto = "Latitude: " + MinhaLocalizacaoListener.latitude + "\n" +
                    "Longitude: " + MinhaLocalizacaoListener.longitude + "\n";

            //Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG).show();
        }


    }
}