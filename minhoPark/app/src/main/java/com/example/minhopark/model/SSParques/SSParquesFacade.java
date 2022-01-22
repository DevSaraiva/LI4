package com.example.minhopark.model.SSParques;

import com.example.minhopark.DataBase.CategoriaDAO;
import com.example.minhopark.DataBase.HorarioDAO;
import com.example.minhopark.DataBase.ParqueDAO;
import com.example.minhopark.model.SSParques.IParques;
import com.example.minhopark.model.SSParques.Parque;

import java.util.List;
import java.util.prefs.Preferences;

public class SSParquesFacade implements IParques {

    CategoriaDAO categorias = new CategoriaDAO();
    HorarioDAO horarios = new HorarioDAO();
    ParqueDAO parques = new ParqueDAO();



    @Override
    public Parque getParque(int id) {
        return parques.getParque(id);
    }

    public void addParque(Parque p){
        parques.addParque(p);
    }


    @Override
    public List<Parque> pesquisa(Preferences p) {
        return null;
    }
}
