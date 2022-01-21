package model;

import java.sql.Blob;
import java.util.List;

public class Parque {

    int parqueID;
    String nome;
    Blob image;
    String endereco;
    String coordenadas;
    int numCriticas;
    int rating;
    List<Horario> horarios;
    List<Categoria> categorias;


    public Parque(int parqueID, String nome, Blob image, String endereco, String coordenadas, int numCriticas, int rating) {
        this.parqueID = parqueID;
        this.nome = nome;
        this.image = image;
        this.endereco = endereco;
        this.coordenadas = coordenadas;
        this.numCriticas = numCriticas;
        this.rating = rating;
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

    public Blob getImage() {
        return image;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public int getNumCriticas() {
        return numCriticas;
    }

    public int getRating() {
        return rating;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }


    public void setParqueID(int parqueID) {
        this.parqueID = parqueID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImg(Blob pathToImg) {
        this.image = pathToImg;
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

    @Override
    public String toString() {
        return "Parque{" +
                "parqueID=" + parqueID +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", numCriticas=" + numCriticas +
                ", rating=" + rating +
                '}';
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
