package com.example.minhopark.model.SSUtilizadores;

import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.prefs.Preferences;

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
