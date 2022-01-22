package com.example.minhopark.model.SSParques;

import com.example.minhopark.DataBase.CategoriaDAO;
import com.example.minhopark.DataBase.HorarioDAO;
import com.example.minhopark.DataBase.ParqueDAO;
import com.example.minhopark.model.SSUtilizadores.Preferencia;

import java.util.List;

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
    public List<Parque> pesquisa(Preferencia p) {
        return null;
    }












}
