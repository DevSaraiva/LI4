package com.example.minhopark.model;

import java.util.Set;
import java.util.TreeSet;

public class Preferences {
    private String loc;
    private int nParques;
    private boolean evitarPortagens;
    private Set<String> tiposParques;

    public Preferences () {
        this.loc = "locDispositivo";
        this.nParques = 10;
        this.evitarPortagens = false;
        this.tiposParques = new TreeSet<>();
        this.addParque("Parque_De_Lazer");
        this.addParque("Parque_Urbano");
        this.addParque("Parque_Infatil");
        this.addParque("Parque_Campismo");
        this.addParque("Parque_Diversoes");
        this.addParque("Parque_Patinagem");
        this.addParque("Parque_Ecologico");
        this.addParque("Parque_Cidade");
        this.addParque("Parque_Estatal");
        this.addParque("Parque_Nacional");
        this.addParque("Jardim");
        this.addParque("Reserva_Natural");

    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getnParques() {
        return nParques;
    }

    public void setnParques(int nParques) {
        this.nParques = nParques;
    }

    public boolean getEvitarPortagens() {
        return evitarPortagens;
    }

    public void setEvitarPortagens(boolean evitarPortagens) {
        this.evitarPortagens = evitarPortagens;
    }

    public Set<String> getTiposParques() {
        return tiposParques;
    }

    public void setTiposParques(Set<String> tiposParques) {
        this.tiposParques = tiposParques;
    }

    public void addParque (String parque) {
        this.tiposParques.add(parque);
    }

    public void removeParque (String parque) {
        this.tiposParques.remove(parque);
    }
}
