package com.example.minhopark.model.SSUtilizadores;

import java.io.Serializable;
import java.util.Set;



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
