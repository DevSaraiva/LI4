package com.example.minhopark.model.SSUtilizadores;

public class SSUtilizadorFacade {
    private Utilizador utilizador;


    Utilizador getUtilizador(){
     //TODO suponho que seja utilizar a funcao do disco e ler o utilizador
        return null; // so para nao dar erro
    }

    void atualizaPreferencias(Preferencia prefs) {
        utilizador.setPreferencia(prefs);
    }

    void atualizaCoordenadas () {
        utilizador.atualizaCoordenadas();
    }

    void adicionaFavorito(Integer id){
        utilizador.adicionaFavorito(id);
    }
    void removeFavorito(Integer id){
        utilizador.removeFavotito(id);
    }

}
