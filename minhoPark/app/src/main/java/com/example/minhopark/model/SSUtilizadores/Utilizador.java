package com.example.minhopark.model.SSUtilizadores;

import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Utilizador {
    String coordenadas;
    Set<Integer> favoritos;
    Preferencia pref;



    public Utilizador(String coordenadas){ // depois adicionar pref e favoritos MUdar
        this.coordenadas = coordenadas;

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

    public void atualizaCoordenadas() {
        if (this.pref.getLoc().equals("LocDispositivo")) {
            //TODO meter coordenadas do dispositivo ir buscar a api maps provavelmente
        } else {
            //TODO ler o que ele escreveu no campo texto
        }
    }




}
