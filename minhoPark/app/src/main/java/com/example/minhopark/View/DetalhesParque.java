package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSParques.Parque;
import com.example.minhopark.model.SSParques.SSParquesFacade;
import com.example.minhopark.model.SSUtilizadores.Preferencia;

import java.util.Set;
import java.util.TreeSet;

public class DetalhesParque extends AppCompatActivity {

    Parque parque;

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
        setContentView(R.layout.activity_detalhes_parque);


        //gerar preferencias

        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        String loc = prefs.getString("chaveLoc", buscarInformacoesGPS());
        int nParques = prefs.getInt("chaveNParques", 10);
        boolean portagens = prefs.getBoolean("chavePortagens", false);
        Set<String> tipos = prefs.getStringSet("chaveListTiposParques", new TreeSet<>());
        Preferencia p = new Preferencia(loc, nParques, portagens, tipos);



        //gerar Favoritos

        Set<Integer> facvoritos = new TreeSet<>();


        //Get Parque

        Connect task = new Connect(p,facvoritos,getIntent().getIntExtra("ParqueID",1));
        try {
            parque = task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        TextView tv1 = (TextView)findViewById(R.id.nomeParque);
        tv1.setText(parque.getNome());


        TextView tv2 = (TextView)findViewById(R.id.Categoria);
        tv2.setText(parque.getCategorias().toString());


        TextView tv3 = (TextView)findViewById(R.id.Horario);
        tv3.setText(parque.getHorarios().toString());


        TextView tv4 = (TextView)findViewById(R.id.estadoOperacional);
        tv4.setText("Operacional");


        TextView tv5 = (TextView)findViewById(R.id.Endereco);
        tv5.setText(parque.getEndereco());



        String coordenadas1[] = parque.getCoordenadas().split(",");
        String coordenadas2[] = p.getLoc().split(",");

        double dist = SSParquesFacade.distance(Double.parseDouble(coordenadas1[0]),Double.parseDouble(coordenadas2[0]),Double.parseDouble(coordenadas1[1]),Double.parseDouble(coordenadas2[1]));


        TextView tv6 = (TextView)findViewById(R.id.Distancia);
        tv6.setText(String.valueOf(dist));


        TextView tv7 = (TextView)findViewById(R.id.rating);
        tv7.setText(String.valueOf(parque.getRating()));







    }




    public String buscarInformacoesGPS() {
        String texto = "ERRO";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(DetalhesParque.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(DetalhesParque.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(DetalhesParque.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return texto;
        }

        LocationManager mlocManager = (LocationManager) getSystemService(DetalhesParque.this.LOCATION_SERVICE);
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