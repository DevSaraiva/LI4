package com.example.minhopark.model.SSUtilizadores;

import java.util.Set;

public class Utilizador {
    String coordenadas;
    Set<Integer> favoritos;
    Preferencia pref;

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
