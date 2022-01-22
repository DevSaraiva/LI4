package com.example.minhopark.model.SSParques;

public class EstadoOperacional {
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
