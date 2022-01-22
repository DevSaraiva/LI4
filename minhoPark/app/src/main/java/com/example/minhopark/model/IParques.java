package com.example.minhopark.model;

import java.util.List;

public interface IParques {

    Parque getParque(int id);
    public void addParque(Parque p);
    List<Parque> pesquisa(Preferences p);

}

