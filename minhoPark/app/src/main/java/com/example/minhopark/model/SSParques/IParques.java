package com.example.minhopark.model.SSParques;

import com.example.minhopark.model.SSUtilizadores.Preferencia;

import java.util.List;

public interface IParques {

    Parque getParque(int id);
    public void addParque(Parque p);
    List<Parque> pesquisa(Preferencia p);

}

