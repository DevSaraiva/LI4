package com.example.minhopark.model.SSParques;

import java.util.List;

public interface IParques {

    Parque getParque(int id);
    public void addParque(Parque p);
    List<Parque> pesquisa(Preferences p);

}

