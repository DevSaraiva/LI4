package com.example.minhopark.model;


import java.time.LocalTime;

public class Horario {

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

    @Override
    public String toString() {
        return "Horario{" +
                "idHorario=" + idHorario +
                ", abertura=" + abertura +
                ", encerramento=" + encerramento +
                ", dia='" + dia + '\'' +
                '}';
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
