package com.example.minhopark.model.SSUtilizadores;

import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Utilizador implements Serializable {
    String coordenadas;
    Set<Integer> favoritos;
    Preferencia pref;



    public Utilizador(Preferencia p, Set<Integer> favoritos){ // depois adicionar pref e favoritos MUdar
        this.coordenadas = p.getLoc();
        this.favoritos = favoritos;
        this.pref = p;

    }


    public Preferencia getPreferencia() {
        return pref;
    }

    public Set<Integer> getFavoritos() {
        return favoritos;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setPreferencia(Preferencia pref) {
        this.pref = pref;
    }

    public void setFavoritos(Set<Integer> favoritos) {
        this.favoritos = favoritos;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void adicionaFavorito(Integer id) {
        this.favoritos.add(id);
    }

    public void removeFavotito(Integer id) {
        this.favoritos.remove(id);
    }


}
