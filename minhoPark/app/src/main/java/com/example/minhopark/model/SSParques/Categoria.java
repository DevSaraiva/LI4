package com.example.minhopark.model.SSParques;


import java.io.Serializable;

public class Categoria implements Serializable {

    String nome;
    String iconePath;


    public Categoria(String nome, String iconePath) {
        this.nome = nome;
        this.iconePath = iconePath;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + nome +
                ", iconePath='" + iconePath + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIconePath() {
        return iconePath;
    }

    public void setIconePath(String iconePath) {
        this.iconePath = iconePath;
    }
}
