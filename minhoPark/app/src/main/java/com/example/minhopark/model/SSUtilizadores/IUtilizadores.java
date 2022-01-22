package com.example.minhopark.model.SSUtilizadores;

public interface IUtilizadores {
    Utilizador getUtilizador(String coordenadas);
    void atualizaPreferencias(Preferencia prefs);
    void adicionaFavorito(Integer id);
    void removeFavorito(Integer id);

}

