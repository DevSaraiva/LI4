package com.example.minhopark.model.SSUtilizadores;

public class SSUtilizadorFacade implements IUtilizadores {
    private Utilizador utilizador;


    public SSUtilizadorFacade(String coordenadas){
        this.utilizador = getUtilizador(coordenadas);
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


}
