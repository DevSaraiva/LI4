package com.example.minhopark.model.SSParques;

import com.example.minhopark.model.SSUtilizadores.Preferencia;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IParques {

    Parque getParque(int id);
    public void addParque(Parque p);
    Set<Parque> pesquisa(Preferencia p);
    Preferencia getPreferencia();

}

