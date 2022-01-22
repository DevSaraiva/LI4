package com.example.minhopark.model.SSParques;

import com.example.minhopark.DataBase.CategoriaDAO;
import com.example.minhopark.DataBase.HorarioDAO;
import com.example.minhopark.DataBase.ParqueDAO;
import com.example.minhopark.model.SSUtilizadores.Preferencia;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SSParquesFacade implements IParques {

    CategoriaDAO categorias;
    HorarioDAO horarios;
    ParqueDAO parques;

    
    public SSParquesFacade(){
        this.categorias = new CategoriaDAO();
        this.horarios = new HorarioDAO();
        this.parques = new ParqueDAO();

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
            for(Parque parque : this.parques.getParques(id)){
                res.add(parque);
            }
        }


        return res;

    }












}
