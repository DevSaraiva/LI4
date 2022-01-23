package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSParques.Parque;
import com.example.minhopark.model.SSParques.SSParquesFacade;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.ExecutionException;


public class  MainActivity extends AppCompatActivity {

    SSParquesFacade ssParquesFacade;

    private class Connect extends AsyncTask<Void, Void, SSParquesFacade> {
        @Override
        protected SSParquesFacade doInBackground(Void... urls) {

            SSParquesFacade ssParquesFacade = new SSParquesFacade(null);

            ssParquesFacade.getParque(1);

            return  ssParquesFacade;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPref = findViewById(R.id.buttonPreferencias);
        Button buttonFav = findViewById(R.id.buttonFavoritos);
        Button buttonProc = findViewById(R.id.buttonProcura);



        buttonPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


        buttonProc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PesquisaActivity.class);
                startActivity(intent);
            }
        });

    }
}