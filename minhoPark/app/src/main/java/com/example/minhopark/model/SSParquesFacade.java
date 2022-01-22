package com.example.minhopark.model;

import com.example.minhopark.DataBase.CategoriaDAO;
import com.example.minhopark.DataBase.HorarioDAO;
import com.example.minhopark.DataBase.ParqueDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSParquesFacade implements IParques {

    CategoriaDAO categorias;
    HorarioDAO horarios;
    ParqueDAO parques;
    Map<String,Parque> favoritos;
    Preferences preferencias;

    
    public SSParquesFacade(){
        this.categorias = new CategoriaDAO();
        this.horarios = new HorarioDAO();
        this.parques = new ParqueDAO();
        this.favoritos = new HashMap<>();
        this.preferencias = new Preferences();
    }



    @Override
    public Parque getParque(int id) {
        return parques.getParque(id);
    }

    @Override
    public void addParque(Parque p){
        parques.addParque(p);
    }

    @Override
    public List<Parque> pesquisa(Preferences p) {
        return null;
    }



    public void savePreferences(){

        try {
        File file = new File("/app/src/main/java/com/example/saves/preferences");

        if (!file.exists()) file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(this.preferencias);
            oos.flush();
            oos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadPrerences() throws FileNotFoundException {

        File toRead = new File("/app/src/main/java/com/example/saves/preferences");

        try {
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.preferencias = (Preferences) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void loadFavoritos(){
        File toRead = new File("/app/src/main/java/com/example/saves/favoritos");

        try {
            FileInputStream fis = new FileInputStream(toRead);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.favoritos = (Map<String,Parque>) ois.readObject();
            ois.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args){

        SSParquesFacade ssParquesFacade;

        ssParquesFacade.savePreferences();

        Preferences preferences = new Preferences();







    }




}
