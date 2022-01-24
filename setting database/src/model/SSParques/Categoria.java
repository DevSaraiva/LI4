package model.SSParques;


import java.io.Serializable;

public class Categoria implements Serializable {

    String nome;
    byte[] icone;


    public Categoria(String nome, byte[] icone) {
        this.nome = nome;
        this.icone = icone;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getIcone() {
        return icone;
    }

    public void setIconePath(byte[] icone) {
        this.icone = icone;
    }


    public String toString() {
        return this.nome+"\n";
    }


}
