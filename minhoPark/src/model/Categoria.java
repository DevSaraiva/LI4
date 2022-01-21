package model;

import java.sql.Blob;

public class Categoria {

    int idCategoria;
    String iconePath;
    String descricao;


    public Categoria(int idCategoria, String iconePath, String descricao) {
        this.idCategoria = idCategoria;
        this.iconePath = iconePath;
        this.descricao = descricao;

    }


    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", iconePath='" + iconePath + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getIconePath() {
        return iconePath;
    }

    public void setIconePath(String iconePath) {
        this.iconePath = iconePath;
    }

    public void setDescrição(String descrição) {
        this.descricao = descricao;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
