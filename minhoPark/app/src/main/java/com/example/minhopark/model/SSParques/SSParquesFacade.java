package com.example.minhopark.model.SSParques;

import com.example.minhopark.DataBase.CategoriaDAO;
import com.example.minhopark.DataBase.HorarioDAO;
import com.example.minhopark.DataBase.ParqueDAO;
import com.example.minhopark.model.SSUtilizadores.IUtilizadores;
import com.example.minhopark.model.SSUtilizadores.Preferencia;
import com.example.minhopark.model.SSUtilizadores.SSUtilizadorFacade;
import com.example.minhopark.model.SSUtilizadores.Utilizador;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SSParquesFacade implements IParques {


    CategoriaDAO categorias;
    HorarioDAO horarios;
    ParqueDAO parques;
    IUtilizadores utilizador;

    
    public SSParquesFacade(String coordenadas){
        this.categorias = new CategoriaDAO();
        this.horarios = new HorarioDAO();
        this.parques = new ParqueDAO();
        this.utilizador = new SSUtilizadorFacade(coordenadas);

    }



    @Override
    public Parque getParque(int id) {
        return parques.getParque(id);
    }

    @Override
    public void addParque(Parque p){
        parques.addParque(p);
    }

    @Override
    public Set<Parque> pesquisa(Preferencia p) {

        Set<Parque> res = new TreeSet<>();


        for(String id : p.getTiposParques()){
            for(Parque parque : this.parques.getParquesFiltered(id)){
                res.add(parque);
            }
        }


        return res;

    }


    public static double distance(double lat1, double lat2, double lon1, double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }


    public double compare(Parque p1, Parque p2)
    {
        String[] coordenadasInicio = this.utilizador.getCoordenadas().split(",");

        String[] coordenadasP1 = p1.getCoordenadas().split(",");
        String[] coordenadasP2 = p2.getCoordenadas().split(",");


        double d1 = distance(Double.parseDouble(coordenadasP1[0]),Double.parseDouble(coordenadasInicio[0]),Double.parseDouble(coordenadasP1[1]),Double.parseDouble(coordenadasInicio[1]));
        double d2 = distance(Double.parseDouble(coordenadasP2[0]),Double.parseDouble(coordenadasInicio[0]),Double.parseDouble(coordenadasP2[1]),Double.parseDouble(coordenadasInicio[1]));

        return d2 - d1;
    }


    public Set<Parque> devolveMaisPerto(double maxDist, double latOrg, double lonOrg){
        Set<Parque> res = new TreeSet<>();

        List<Parque> allParques = this.parques.getParques();

        for(Parque p : allParques){

            String[] coordenadas = p.getCoordenadas().split(";");

            if(distance(latOrg,Double.parseDouble(coordenadas[1]),lonOrg,Double.parseDouble(coordenadas[1])) <= maxDist){
                res.add(p);
            }
        }


        return res;
    }












}
