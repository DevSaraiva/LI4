package com.example.minhopark.model.SSParques;

import java.io.Serializable;

public class EstadoOperacional implements Serializable {
    private boolean estado;

    public EstadoOperacional(boolean b) {
        this.estado = b;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
