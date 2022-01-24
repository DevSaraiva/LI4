package com.example.minhopark.model.SSParques;


import java.io.Serializable;
import java.time.LocalTime;

public class Horario implements Serializable {

    int idHorario;
    LocalTime abertura;
    LocalTime encerramento;
    String dia;

    public Horario(int idHorario,LocalTime abertura, LocalTime encerramento, String dia) {
        this.idHorario = idHorario;
        this.abertura = abertura;
        this.encerramento = encerramento;
        this.dia = dia;
    }

    public String toString() {
        return dia + ": " + abertura +" -> " + encerramento+"\n";
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public LocalTime getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalTime abertura) {
        this.abertura = abertura;
    }

    public LocalTime getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(LocalTime encerramento) {
        this.encerramento = encerramento;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }


}
