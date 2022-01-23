package com.example.minhopark.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.MinhoPark.R;
import com.example.MinhoPark.databinding.ActivityMaps2Binding;
import com.example.minhopark.View.directionHelpers.FetchURL;
import com.example.minhopark.model.SSParques.Parque;
import com.example.minhopark.model.SSParques.SSParquesFacade;
import com.example.minhopark.model.SSUtilizadores.Preferencia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;


public class PesquisaActivity extends AppCompatActivity {


    private class Connect extends AsyncTask<Void, Void, Set<Parque>> {


        Preferencia preferencia;
        Set<Integer> favoritos;


        public  Connect(Preferencia p , Set<Integer> favoritos){
            this.preferencia = p;
            this.favoritos = favoritos;
        }

        @Override
        protected Set<Parque> doInBackground(Void ...params) {


                SSParquesFacade ssParquesFacade = new SSParquesFacade(preferencia, favoritos);

                Set<Parque> parques = ssParquesFacade.pesquisa(preferencia);

                return parques;
        }

    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa2);



        //buttons

        //gerar preferencias

        SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        String loc = prefs.getString("chaveLoc", buscarInformacoesGPS());
        int nParques = prefs.getInt("chaveNParques", 10);
        boolean portagens = prefs.getBoolean("chavePortagens", false);
        Set<String> tipos = prefs.getStringSet("chaveListTiposParques", new TreeSet<>());
        Preferencia p = new Preferencia(loc, nParques, portagens, tipos);


        //gerar Favoritos

        Set<Integer> facvoritos = new TreeSet<>();

        //gerar butões

        Set<Parque> parques = null;
        Connect task = new Connect(p,facvoritos);
        try {
            parques = task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        LinearLayout btn_layer= (LinearLayout) findViewById(R.id.btnLayout);

        for (Parque parque: parques)
        {

           Button button = new Button(this);
           button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
           button.setId(parque.getParqueID());
           button.setText(parque.getNome());

           btn_layer.addView(button);
        }





    }


    public String buscarInformacoesGPS() {
        String texto = "ERRO";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(PesquisaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(PesquisaActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(PesquisaActivity.this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);
            return texto;
        }

        LocationManager mlocManager = (LocationManager) getSystemService(PesquisaActivity.this.LOCATION_SERVICE);
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