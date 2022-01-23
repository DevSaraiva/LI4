package com.example.minhopark.model.SSUtilizadores;

import java.io.Serializable;

public class SSUtilizadorFacade implements IUtilizadores, Serializable {
    private Utilizador utilizador;


    public SSUtilizadorFacade(String coordenadas){
        this.utilizador = new Utilizador(coordenadas);
    }

    public Utilizador getUtilizador(String coordenadas){
        Utilizador  u = new Utilizador(coordenadas);
        return u;
    }

    public void atualizaPreferencias(Preferencia prefs) {
        utilizador.setPreferencia(prefs);
    }

    public void atualizaCoordenadas () {
        utilizador.atualizaCoordenadas();
    }

    public void adicionaFavorito(Integer id){
        utilizador.adicionaFavorito(id);
    }
    public void removeFavorito(Integer id){
        utilizador.removeFavotito(id);
    }

    @Override
    public String getCoordenadas() {
        return utilizador.getCoordenadas();
    }


    public Preferencia getPreferencia(){
        return this.utilizador.getPreferencia();
    }


}
