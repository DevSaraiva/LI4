package com.example.minhopark.model.SSUtilizadores;

import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Utilizador {
    String coordenadas;
    Set<Integer> favoritos;
    Preferencia pref;



    public Utilizador(String coordenadas){
        this.coordenadas = coordenadas;

        try {

            if(!loadFavoritos()) this.favoritos = new TreeSet<>();
            if(!loadPrerences()) this.pref = new Preferencia();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public Preferencia getPreferencia() {
        return pref;
    }

    public Set<Integer> getFavoritos() {
        return favoritos;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setPreferencia(Preferencia pref) {
        this.pref = pref;
    }

    public void setFavoritos(Set<Integer> favoritos) {
        this.favoritos = favoritos;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void adicionaFavorito(Integer id) {
        this.favoritos.add(id);
    }

    public void removeFavotito(Integer id) {
        this.favoritos.remove(id);
    }

    public void atualizaCoordenadas() {
        if (this.pref.getLoc().equals("LocDispositivo")) {
            //TODO meter coordenadas do dispositivo ir buscar a api maps provavelmente
        } else {
            //TODO ler o que ele escreveu no campo texto
        }
    }


    public void savePreferences(){

        try {
            File file = new File("/app/src/main/java/com/example/saves/preferences");

            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this.pref);
            oos.flush();
            oos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean loadPrerences() throws FileNotFoundException {

        try {

            File toRead = new File("/app/src/main/java/com/example/saves/preferences");
            if(!toRead.exists()) return false;

            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.pref = (Preferencia) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;


    }


    public boolean loadFavoritos(){


        try {

            File toRead = new File("/app/src/main/java/com/example/saves/favoritos");

            if(!toRead.exists()) return  false;

            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.favoritos = (TreeSet<Integer>) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


    public void saveFavoritos(){
        try {
            File file = new File("/app/src/main/java/com/example/saves/favoritos");

            if (!file.exists()) file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this.favoritos);
            oos.flush();
            oos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String args[]){

        Utilizador u = new Utilizador("x:10,y:20");

        u.savePreferences();


    }








}
