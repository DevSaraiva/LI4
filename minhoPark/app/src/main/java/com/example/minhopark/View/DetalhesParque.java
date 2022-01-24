package com.example.minhopark.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.MinhoPark.R;
import com.example.minhopark.model.SSParques.Categoria;
import com.example.minhopark.model.SSParques.Horario;
import com.example.minhopark.model.SSParques.Parque;
import com.example.minhopark.model.SSParques.SSParquesFacade;
import com.example.minhopark.model.SSUtilizadores.Preferencia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DetalhesParque extends FragmentActivity implements OnMapReadyCallback {

    Parque parque;
    private GoogleMap mMap;

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

        Set<String> favoritosString = prefs.getStringSet("chaveFavoritos", new TreeSet<>());
        Set<Integer> favoritos = new TreeSet<>();
        for (String s : favoritosString) {
            favoritos.add(Integer.parseInt(s));
        }



        //mapa


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        //Get Parque

        Connect task = new Connect(p,favoritos,getIntent().getIntExtra("parqueID",1));
        try {
            parque = task.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageButton buttonFav = findViewById(R.id.imageButtonFav);

        // ja esta nos favoritos
        if (favoritos.contains(parque.getParqueID())) {
            buttonFav.setActivated(true);
        } else {
            buttonFav.setActivated(false);
        }

        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonFav.isActivated()){
                    favoritos.remove(parque.getParqueID());
                    buttonFav.setActivated(false);
                } else {
                    favoritos.add(parque.getParqueID());
                    buttonFav.setActivated(true);
                }
                // grava toda a vez que clica na estrela secalhar meter só a gravar quando sai deste menu (Falta botao voltar atras)
                Set<String> favoritosGravar = new TreeSet<>();
                for (Integer x : favoritos) {
                    favoritosGravar.add(x.toString());
                }
                SharedPreferences prefs = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putStringSet("chaveFavoritos", favoritosGravar);
                editor.commit();

            }
        });

        TextView tv1 = (TextView)findViewById(R.id.nomeParque);
        tv1.setText(parque.getNome());


        TextView tv2 = (TextView)findViewById(R.id.Categoria);
        tv2.setText(ListToStringCategoria(parque.getCategorias()));


        TextView tv3 = (TextView)findViewById(R.id.Horario);
        tv3.setText(ListToStringHorario(parque.getHorarios()));


       TextView tv4 = (TextView)findViewById(R.id.estadoOperacional);
       tv4.setText("Operacional");


        TextView tv5 = (TextView)findViewById(R.id.Endereco);
        tv5.setText(parque.getEndereco());



        String coordenadas1[] = parque.getCoordenadas().split(",");
        String coordenadas2[] = p.getLoc().split(",");

        double dist = SSParquesFacade.distance(Double.parseDouble(coordenadas1[0]),Double.parseDouble(coordenadas2[0]),Double.parseDouble(coordenadas1[1]),Double.parseDouble(coordenadas2[1]));


        TextView tv6 = (TextView)findViewById(R.id.Distancia);
        tv6.setText(String.valueOf("Distancia ->" + dist));


        TextView tv7 = (TextView)findViewById(R.id.rating);
        tv7.setText(String.valueOf("Rating ->" + parque.getRating()));


        byte[] picData = parque.getImage();
        ImageView imgPerson = (ImageView) findViewById(R.id.parkImg);
        Bitmap bitmap = BitmapFactory.decodeByteArray(picData, 0, picData.length);
        imgPerson.setImageBitmap(bitmap);


        //Botao para IR para o mapa

        Button botaoIr = findViewById(R.id.buttonIr);
        botaoIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalhesParque.this, MapsActivity.class);
                intent.putExtra("loc",p.getLoc());
                intent.putExtra("coordenadas", parque.getCoordenadas());
                startActivity(intent);
            }
        });




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


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        String[] coordenads = parque.getCoordenadas().split(",");

        LatLng coord = new LatLng(Double.parseDouble(coordenads[0]),Double.parseDouble(coordenads[1]));

        mMap.addMarker(new MarkerOptions().position(coord).title(parque.getNome()));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 12.0f));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public String ListToStringHorario(List<Horario> l) {
        StringBuilder sb = new StringBuilder();
        for(Horario h : l){
            sb.append(h.toString());
        }
        return sb.toString();
    }



    public String ListToStringCategoria(List<Categoria> l) {
        StringBuilder sb = new StringBuilder();
        for(Categoria c : l){
            sb.append(c.toString());
        }
        return sb.toString();
    }








}