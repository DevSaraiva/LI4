package com.example.minhopark.model.SSParques;


import java.io.Serializable;
import java.util.List;

public class Parque implements Serializable {

    int parqueID;
    String nome;
    byte[] image;
    String endereco;
    String coordenadas;
    int numCriticas;
    float rating;
    List<Horario> horarios;
    List<Categoria> categorias;
    EstadoOperacional estadoOp;

    public Parque(int parqueID, String nome, byte[] image, String endereco, String coordenadas, int numCriticas, float rating, List<Horario> horarios, List<Categoria> categorias) {
        this.parqueID = parqueID;
        this.nome = nome;
        this.image = image;
        this.endereco = endereco;
        this.coordenadas = coordenadas;
        this.numCriticas = numCriticas;
        this.rating = rating;
        this.horarios = horarios;
        this.categorias = categorias;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getParqueID() {
        return parqueID;
    }

    public String getNome() {
        return nome;
    }

    public byte[] getImage() {
        return image;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public int getNumCriticas() {
        return numCriticas;
    }

    public float getRating() {
        return rating;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setParqueID(int parqueID) {
        this.parqueID = parqueID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImg(byte[] image) {
        this.image = image;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setNumCriticas(int numCriticas) {
        this.numCriticas = numCriticas;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Parque{" +
                "parqueID=" + parqueID +
                ", nome='" + nome + '\'' +
                ", image='" + image + '\'' +
                ", endereco='" + endereco + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", numCriticas=" + numCriticas +
                ", rating=" + rating +
                ", horarios=" + horarios +
                ", categorias=" + categorias +
                '}';
    }
}
