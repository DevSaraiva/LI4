package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Preferences {
    private String loc;
    private int nParques;
    private boolean evitarPortagens;
    private List<String> tiposParques;

    public Preferences () {
        this.loc = "locDispositivo";
        this.nParques = 10;
        this.evitarPortagens = false;
        this.tiposParques = new ArrayList<>();
        // adicionar no inicio todos os parques
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

    public List<String> getTiposParques() {
        return tiposParques;
    }

    public void setTiposParques(List<String> tiposParques) {
        this.tiposParques = tiposParques;
    }


}
