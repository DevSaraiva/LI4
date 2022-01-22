package com.example.minhopark.DataBase;


import com.example.minhopark.model.SSParques.Categoria;
import com.example.minhopark.model.SSParques.Horario;
import com.example.minhopark.model.SSParques.Parque;

import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ParqueDAO {
    private Connection connection;
    private CategoriaDAO categoriaDAO;
    private HorarioDAO horarioDAO;

    public ParqueDAO() {
        try {
            this.connection = ConnectDB.getConnection();
            this.categoriaDAO = new CategoriaDAO();
            this.horarioDAO = new HorarioDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Categoria> getCategorias(int parqueID) {

        try {
            String sql = "SELECT * FROM Parques_has_Categorias WHERE Parques_idParque=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parqueID);
            ResultSet rs = stmt.executeQuery();
            List<Categoria> categorias = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString(1);
                Blob imagem = rs.getBlob(2);
                Categoria categoria = new Categoria(id, null);
                categorias.add(categoria);
            }

            return categorias;

        } catch(SQLException e){

            e.printStackTrace();
        }
            return null;
    }



    public Parque getParque(int parqueID) {
        String sql = "SELECT * FROM Parques WHERE iDParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, parqueID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                Blob imagem = rs.getBlob(3);
                String filename = "parque" + id;
                String filePath = ConnectDB.convertToFile(imagem, filename);
                String endereco = rs.getString(4);
                String coordenadas = rs.getString(5);
                int nrCriticas = rs.getInt(6);
                int rating = rs.getInt(7);

                List<Categoria> categorias = this.getCategorias(parqueID);
                List<Horario> horarios = this.horarioDAO.getHorarios(parqueID);

                return new Parque(id,nome, filePath,endereco,coordenadas,nrCriticas,rating,horarios,categorias);

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCategoriaToPark(int parque, String categoria){
        String sql = "INSERT INTO Parques_has_Categorias(Parques_idParque,Categorias_idCategoria) VALUES(?,?)";

        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque);
            stmt.setString(2,categoria);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean addParque(Parque parque){
        String sql = "INSERT INTO Parques(idParque,Nome,Imagem,Endereco,Coordenadas,NrCriticas,Rating) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque.getParqueID());
            stmt.setString(2,parque.getNome());
            stmt.setBlob(3, ConnectDB.convertFileContentToBlob(parque.getImage()));
            stmt.setString(4, parque.getEndereco());
            stmt.setString(5,parque.getCoordenadas());
            stmt.setInt(6,parque.getNumCriticas());
            stmt.setFloat(7,parque.getRating());
            stmt.execute();


            for(Categoria c : parque.getCategorias()){
                this.addCategoriaToPark(parque.getParqueID(),c.getNome());
            }

            for(Horario h : parque.getHorarios()){
                this.horarioDAO.addHorario(parque.getParqueID(),h);
            }



            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setParque(Parque parque){
        String sql = "UPDATE Parques Nome=?, Imagem=?, Endereco=?, Coordenadas=?, NrCriticas=?, Rating=?,  WHERE idParque=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,parque.getParqueID());
            stmt.setString(2,parque.getNome());
            stmt.setBlob(3, (InputStream) null);
            stmt.setString(4, parque.getEndereco());
            stmt.setString(5,parque.getCoordenadas());
            stmt.setInt(6,parque.getNumCriticas());
            stmt.setFloat(7,parque.getRating());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeParque(int id){
        String sql ="DELETE FROM Parques WHERE idParque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Parque> getParques(String idCategoria){
        List<Parque> parques = new ArrayList<>();
        String sql = "SELECT * FROM Parques_has_Categorias WHERE Categorias_idCategoria=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,idCategoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int idParque = rs.getInt(1);
                Parque parque = this.getParque(idParque);
                parques.add(parque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parques;
    }



    public static void main(String args[]){
        ParqueDAO dao = new ParqueDAO();

        String path = "/home/saraiva/Desktop/University/LI4/minhoPark/app/src/main/java/com/example/imgs/teste.jpeg";
        path.trim();

        File f = new File(path);

        if(f.exists()){
            System.out.println("ola");
        }else{
            System.out.println("adeus");
        }


        List<Categoria> categorias = new ArrayList<>();
        List<Horario> horarios = new ArrayList<>();
        Categoria categoria = new Categoria("Parque_De_Lazer",null);
        categorias.add(categoria);

        Parque p = new Parque(1,"Florestal",path,"rua matos","x:13,y:14,z:50",2,5,horarios,categorias);

        dao.removeParque(1);
        dao.addParque(p);
        System.out.println(dao.getParques("Parque_De_Lazer"));

    }

}

